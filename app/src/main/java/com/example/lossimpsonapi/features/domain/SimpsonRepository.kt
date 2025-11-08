package com.example.lossimpsonapi.features.domain

interface SimpsonRepository {

    suspend fun getAllSimpson (): Result <List<Simpson>>
}