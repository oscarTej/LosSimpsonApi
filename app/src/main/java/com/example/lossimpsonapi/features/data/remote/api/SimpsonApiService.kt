package com.example.lossimpsonapi.features.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface SimpsonApiService {
    @GET("characters")

    suspend fun getAllSimpson():Response<CharactersResponse>
}