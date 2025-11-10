package com.example.lossimpsonapi.features.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lossimpsonapi.core.api.ApiClient
import com.example.lossimpsonapi.features.data.SimpsonDataRepository
import com.example.lossimpsonapi.features.data.remote.api.SimpsonApiDataSource
import com.example.lossimpsonapi.features.domain.GetAllSimpsonUseCase
import com.example.lossimpsonapi.features.presentation.list.SimpsonListViewModel

class SimpsonListVMFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val apiClient = ApiClient()
        val dataSource = SimpsonApiDataSource(apiClient)
        val repository = SimpsonDataRepository(dataSource)
        val useCase = GetAllSimpsonUseCase(repository)
        return SimpsonListViewModel(useCase) as T
    }

}