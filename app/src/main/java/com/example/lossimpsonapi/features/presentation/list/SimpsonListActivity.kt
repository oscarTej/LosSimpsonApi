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
        viewModel = ViewModelProvider(this, SimpsonListVMFactory())
            .get(SimpsonListViewModel::class.java)

        setupRecycler()
        observeUi()

        viewModel.loadAllSimpson()
    }

    private fun setupRecycler() = with(binding) {
        rvSimpsons.layoutManager = LinearLayoutManager(this@SimpsonListActivity)
    }

    private fun observeUi() {
        viewModel.uiState.observe(this) { state ->
            // loading
            binding.pbLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            // error
            if (state.error != null) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = state.error
                binding.rvSimpsons.visibility = View.GONE
            } else {
                binding.tvError.visibility = View.GONE
            }
            // list
            if (state.simpson.isNotEmpty()) {
                binding.rvSimpsons.visibility = View.VISIBLE
                binding.rvSimpsons.adapter = SimpsonAdapter(state.simpson)
            }
        }
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




