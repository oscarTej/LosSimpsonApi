package com.example.lossimpsonapi.features.presentation.list

import com.example.lossimpsonapi.core.error.ErrorApp
import com.example.lossimpsonapi.features.domain.Simpson

 data class SimpsonUiState (
   val isLoading: Boolean =false,
    val simpson : List<Simpson> = emptyList(),
   val error : String? = null
) {
}