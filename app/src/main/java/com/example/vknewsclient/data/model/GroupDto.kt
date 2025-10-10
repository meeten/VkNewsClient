package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

class GroupDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("photo_200") val photoGroupUrl: String,
)

