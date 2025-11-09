package com.example.lossimpsonapi.features.data.remote.api

import com.google.gson.annotations.SerializedName

data class CharactersResponse (
    @SerializedName("results") val results: List<SimpsonApiModel>?,
    @SerializedName("data") val data: List<SimpsonApiModel>?
){
    val items: List<SimpsonApiModel> get() = results ?: data ?: emptyList()
}