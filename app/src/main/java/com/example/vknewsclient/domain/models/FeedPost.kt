package com.example.vknewsclient.domain.models

data class FeedPost(
    val id: Long,
    val ownerId: Long,
    val publicImageUrl: String,
    val publicName: String,
    val publicationTime: String,
    val postContent: String,
    val postContentImageUrl: String,
    val isLiked: Boolean,
    val statistics: List<StatisticItem>,
)