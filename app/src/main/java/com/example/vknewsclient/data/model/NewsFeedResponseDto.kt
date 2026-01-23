package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class NewsFeedResponseDto(
    @SerializedName("response") val newsFeedContent: NewsFeedContentDto,
    @SerializedName("error") val error: ErrorDto?
)