package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class IgnoreFeedPostResponseDto(
    @SerializedName("response") val ignoreFeedPostStatus: IgnoreFeedPostStatusDto,
)
