package com.example.lossimpsonapi.features.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lossimpsonapi.databinding.ItemSimpsonBinding
import com.example.lossimpsonapi.features.domain.Simpson

class SimpsonViewHolder (
    private val binding : ItemSimpsonBinding,
    private val onItemClick :((Simpson) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Simpson) = with(binding) {
        tvName.text = model.name
        tvOccupation.text = model.occupation
        // te muestra la primera frase y si no tiene devuelve no tiene frases (Listas)
        tvPhrasesCount.text = model.phrases.firstOrNull() ?: "No tiene frases "
        ivAvatar.load(model.imageUrl) {
            crossfade(true)
            placeholder(android.R.drawable.sym_def_app_icon)
            error(android.R.drawable.ic_delete)

        }
        root.setOnClickListener { onItemClick?.invoke(model) }
    }
}