package com.example.vknewsclient.data.model

import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.Profile
import com.google.gson.annotations.SerializedName

data class CommentsContentDto(
    @SerializedName("items") val comments: List<CommentItemDto>,
    @SerializedName("profiles") val profiles: List<ProfileDto>,
)