package com.example.lossimpsonapi.features.data.remote.api

data class SimpsonApiModel (
    val id: Int,
    val name: String,
    val occupation: String,
    val phrases: List<String>,
    val portrait_path: String
)
