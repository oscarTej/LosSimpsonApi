package com.example.lossimpsonapi.features.domain

 data class Simpson(
     val id: Int,
     val name: String,
     val occupation: String,
     val phrases: List<String>,
     val imageUrl: String
)
