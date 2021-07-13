package com.ojanbelajar.madeprojectojan.di


import com.ojanbelajar.core.domain.usecase.CharacterInteractor
import com.ojanbelajar.core.domain.usecase.CharacterUseCase
import com.ojanbelajar.madeprojectojan.detail.DetailViewModel
import com.ojanbelajar.madeprojectojan.home.HomeViewModel
import com.ojanbelajar.madeprojectojan.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CharacterUseCase> { CharacterInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}