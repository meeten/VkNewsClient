package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("error_code") val errorCode: Int,
    @SerializedName("error_msg") val errorMessage: String
)
