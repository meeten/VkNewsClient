package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttachmentsDto(
    @SerialName("photo") val photo: PhotoDto,
)
