package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class LikesResponseDto(
    @SerializedName("response") val likes: LikesCountDto,
)