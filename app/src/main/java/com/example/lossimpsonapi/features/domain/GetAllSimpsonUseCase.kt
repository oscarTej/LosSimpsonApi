package com.example.lossimpsonapi.features.domain

class GetAllSimpsonUseCase (private val simpsonRepository: SimpsonRepository) {

    suspend operator fun invoke(): Result<List<Simpson>> {
        return simpsonRepository.getAllSimpson()
    }
}