package com.ojanbelajar.core.data.source.remote

import com.ojanbelajar.core.data.source.remote.network.ApiResponse
import com.ojanbelajar.core.data.source.remote.network.ApiService
import com.ojanbelajar.core.data.source.remote.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource (private val apiService: ApiService){
    suspend fun getCharacter(): Flow<ApiResponse<List<CharacterResponse>>>{
        return flow {
            try {
                val response = apiService.getCharacter()
                val data = response.results
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    suspend fun getSearch(name: String): Flow<ApiResponse<List<CharacterResponse>>>{
        return flow {
            try {
                val response = apiService.getSearch(name)
                val data = response.results
                if (data.isNotEmpty()){
                    emit((ApiResponse.Success(response.results)))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}