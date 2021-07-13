package com.ojanbelajar.core.data.source.remote.network

import com.ojanbelajar.core.data.source.remote.response.ListCharacterResponse
import retrofit2.http.*

interface ApiService {

    @GET("character")
    suspend fun getCharacter(): ListCharacterResponse

    @GET
    suspend fun getSearch(@Url url: String): ListCharacterResponse
}