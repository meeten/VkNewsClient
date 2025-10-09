package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GroupDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("photo_200") val photoGroupUrl: String,
)

