package com.example.lossimpsonapi.features.data.remote.api

import com.example.lossimpsonapi.features.domain.Simpson

fun SimpsonApiModel.toModel(): Simpson {

    val fullImageUrl = "https://cdn.thesimpsonsapi.com/500${portrait_path}"
    return Simpson(this.id,this.name,this.occupation,this.phrases,imageUrl = fullImageUrl)
}