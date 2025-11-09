package com.example.lossimpsonapi.features.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lossimpsonapi.features.domain.GetAllSimpsonUseCase
import kotlinx.coroutines.launch

class SimpsonListViewModel(val getAllSimpsonUseCase: GetAllSimpsonUseCase) : ViewModel() {

    private val _uiState = MutableLiveData(SimpsonUiState())
    val uiState: LiveData<SimpsonUiState> = _uiState

    fun loadAllSimpson() {
        _uiState.value = _uiState.value?.copy(isLoading = true, error = null)

        viewModelScope.launch {
            val result = getAllSimpsonUseCase()
            result.fold(
                onSuccess = { lista ->
                    _uiState.setValue(
                        SimpsonUiState(
                            isLoading = false,
                            error = null,
                            Simpson = lista
                        )
                    )
                }, onFailure = { throwable ->
                    val message = throwable.message ?: "EEEEEError "
                    _uiState.setValue(
                        SimpsonUiState(
                            isLoading = false,
                            error = message,
                            Simpson = emptyList()
                        )
                    )

                }

            )

        }
    }
}