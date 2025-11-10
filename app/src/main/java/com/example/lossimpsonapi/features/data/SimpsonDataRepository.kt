package com.example.lossimpsonapi.features.data

import com.example.lossimpsonapi.features.data.remote.api.SimpsonApiDataSource
import com.example.lossimpsonapi.features.domain.Simpson
import com.example.lossimpsonapi.features.domain.SimpsonRepository

class SimpsonDataRepository ( val simpsonApiDataSource: SimpsonApiDataSource) : SimpsonRepository {
    override suspend fun getAllSimpson(): Result<List<Simpson>> {
        return simpsonApiDataSource.getAllSimpson()
    }
}