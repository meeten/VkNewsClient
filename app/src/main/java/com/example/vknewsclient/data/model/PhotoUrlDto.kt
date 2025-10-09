package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PhotoUrlDto(
    @SerialName("url") val photoUrl: String,
)