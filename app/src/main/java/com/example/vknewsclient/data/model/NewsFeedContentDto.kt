package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class NewsFeedContentDto(
    @SerializedName("items") val posts: List<FeedPostDto>,
    @SerializedName("groups") val groups: List<GroupDto>,
)