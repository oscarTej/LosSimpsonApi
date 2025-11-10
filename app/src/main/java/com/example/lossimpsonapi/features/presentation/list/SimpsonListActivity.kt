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
import com.example.lossimpsonapi.features.presentation.factory.SimpsonListVMFactory
import com.example.lossimpsonapi.features.presentation.observer.SimpsonListObserver


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
         val observer = SimpsonListObserver(binding)
        observer.observeUi(this, viewModel)

        viewModel.loadAllSimpson()
    }

    private fun setupRecycler() = with(binding) {
        rvSimpsons.layoutManager = LinearLayoutManager(this@SimpsonListActivity)
    }


}








