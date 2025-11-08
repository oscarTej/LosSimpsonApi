package com.example.lossimpsonapi.features.domain

class GetAllSimpsonUseCase (private val simpsonRepository: SimpsonRepository) {

    operator suspend fun invoke(): Result<List<Simpson>{
        return simpsonRepository.getAllSimpson()
    }
}