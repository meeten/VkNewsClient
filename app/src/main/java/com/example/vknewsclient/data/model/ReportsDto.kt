package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportsDto(
    @SerialName("count") val count: Int,
)