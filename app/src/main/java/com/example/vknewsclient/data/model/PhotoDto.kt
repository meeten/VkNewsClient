package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    @SerialName("id") val id: String,
    @SerialName("sizes") val photoUrls: List<PhotoUrlDto>,
)