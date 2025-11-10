package com.example.lossimpsonapi.features.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lossimpsonapi.databinding.ItemSimpsonBinding
import com.example.lossimpsonapi.features.domain.Simpson

class SimpsonAdapter (
    private val simpsonList : List<Simpson>,
    private val onItemClick : ((Simpson) -> Unit)? = null
): RecyclerView.Adapter<SimpsonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpsonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimpsonBinding.inflate(inflater,parent,false)

        return SimpsonViewHolder(binding,onItemClick)
    }

    override fun onBindViewHolder(holder: SimpsonViewHolder, position: Int) {
        holder.bind(simpsonList[position])
    }

    override fun getItemCount(): Int {
        return simpsonList.size
    }
}