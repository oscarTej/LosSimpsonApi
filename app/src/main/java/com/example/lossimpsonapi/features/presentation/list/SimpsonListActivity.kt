package com.example.lossimpsonapi.features.presentation.list

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lossimpsonapi.core.api.ApiClient
import com.example.lossimpsonapi.databinding.ActivitySimpsonListBinding
import com.example.lossimpsonapi.features.data.SimpsonDataRepository
import com.example.lossimpsonapi.features.data.remote.api.SimpsonApiDataSource
import com.example.lossimpsonapi.features.domain.GetAllSimpsonUseCase
import com.example.lossimpsonapi.features.domain.Simpson
import com.example.lossimpsonapi.features.presentation.adapter.SimpsonAdapter


class SimpsonListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpsonListBinding

    private lateinit var viewModel: SimpsonListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpsonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSimpsons.layoutManager = LinearLayoutManager(this)
        binding.rvSimpsons.adapter = SimpsonAdapter(emptyList())

        viewModel = ViewModelProvider(this, SimpsonListVMFactory())[SimpsonListViewModel::class.java]

        // Estado UI
        viewModel.uiState.observe(this) { state ->
            binding.pbLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE

            if (state.error != null) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = state.error
                binding.rvSimpsons.visibility = View.GONE
            } else {
                binding.tvError.visibility = View.GONE
                // Si hay datos, recreamos el adapter PASANDO LA LISTA POR PARÁMETRO
                if (state.Simpson.isNotEmpty()) {
                    binding.rvSimpsons.adapter = SimpsonAdapter(simpsonList = state.Simpson)
                    binding.rvSimpsons.visibility = View.VISIBLE
                }
            }
        }

        // Dispara carga
        viewModel.loadAllSimpson()
    }
}
private class SimpsonListVMFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val apiClient = ApiClient()
        val dataSource = SimpsonApiDataSource(apiClient)
        val repository = SimpsonDataRepository(dataSource)
        val useCase = GetAllSimpsonUseCase(repository)
        return SimpsonListViewModel(useCase) as T
    }
}




