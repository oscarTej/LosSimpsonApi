package com.example.lossimpsonapi.features.data.remote.api

import com.example.lossimpsonapi.features.domain.Simpson

fun SimpsonApiModel.toModel(): Simpson {

    return Simpson(this.id,this.name,this.occupation,this.phrases,this.imageUrl)
}