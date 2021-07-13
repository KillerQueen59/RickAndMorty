package com.ojanbelajar.core.di

import androidx.room.Room
import com.ojanbelajar.core.data.source.CharacterRepository
import com.ojanbelajar.core.data.source.local.LocalDataSource
import com.ojanbelajar.core.data.source.local.room.CharacterDatabase
import com.ojanbelajar.core.data.source.remote.RemoteDataSource
import com.ojanbelajar.core.data.source.remote.network.ApiService
import com.ojanbelajar.core.domain.repository.ICharacterRepository
import com.ojanbelajar.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<CharacterDatabase>().characterDao()
    }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("ojan".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CharacterDatabase::class.java, "character.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "rickandmortyapi.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/bYRkyoK5zVkcxQ8rLbpK5SV4h1UhO62CKLlDkwkYRP0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}


val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICharacterRepository> {
        CharacterRepository(
            get(),
            get(),
            get()
        )
    }
}