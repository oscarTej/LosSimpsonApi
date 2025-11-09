package com.example.lossimpsonapi.features.data.remote.api

import com.example.lossimpsonapi.core.api.ApiClient
import com.example.lossimpsonapi.core.error.ErrorApp
import com.example.lossimpsonapi.features.domain.Simpson

class SimpsonApiDataSource (private val apiClient: ApiClient) {

    suspend fun getAllSimpson(): Result<List<Simpson>> {

        val apiService = apiClient.createService(SimpsonApiService::class.java)

        return try{
            val response = apiService.getAllSimpson()
            if(response.isSuccessful){
                val body = response.body()
                val list = body?.items
                if(list != null){
                    val listSimpson = list.map { it.toModel() }
                    Result.success(listSimpson)
                }else {
                    // el servidor respondio pero el body viene vacio
                    Result.failure(ErrorApp.EmptyBodyError)
                }
            }else{
                Result.failure(ErrorApp.ServerError)
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }


}