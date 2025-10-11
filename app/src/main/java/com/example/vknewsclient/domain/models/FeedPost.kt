package com.example.vknewsclient.domain.models

data class FeedPost(
    val id: Int,
    val publicImageUrl: String,
    val publicName: String,
    val publicationTime: Int,
    val postContent: String,
    val postContentImageUrl: String,
    val statistics: List<StatisticItem>,
)