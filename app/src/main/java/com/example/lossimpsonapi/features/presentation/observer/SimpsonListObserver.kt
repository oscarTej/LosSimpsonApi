package com.example.lossimpsonapi.features.presentation.observer

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.lossimpsonapi.databinding.ActivitySimpsonListBinding
import com.example.lossimpsonapi.features.presentation.adapter.SimpsonAdapter
import com.example.lossimpsonapi.features.presentation.list.SimpsonListViewModel

class SimpsonListObserver (
    private val binding: ActivitySimpsonListBinding
) {
    fun observeUi(owner: LifecycleOwner, viewModel: SimpsonListViewModel) {
        viewModel.uiState.observe(owner) { state ->
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