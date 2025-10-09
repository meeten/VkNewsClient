package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsFeedContentDto(
    @SerialName("items") val newsFeed: List<FeedPostDto>,
    @SerialName("groups") val groups: List<GroupDto>,
)