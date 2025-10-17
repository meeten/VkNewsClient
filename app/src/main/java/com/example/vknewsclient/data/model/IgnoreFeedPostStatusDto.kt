package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

class IgnoreFeedPostStatusDto(
    @SerializedName("status") val ignoreStatus: Boolean,
)