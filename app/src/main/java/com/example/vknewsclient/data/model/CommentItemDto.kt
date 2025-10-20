package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class CommentItemDto(
    @SerializedName("id") val id: Long,
    @SerializedName("from_id") val profileId: Long,
    @SerializedName("date") val dateUrl: Int,
    @SerializedName("text") val text: String,
)