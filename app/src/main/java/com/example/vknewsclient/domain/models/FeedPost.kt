package com.example.vknewsclient.domain.models

data class FeedPost(
    val id: String,
    val publicImageUrl: String,
    val publicName: String,
    val publicationTime: String,
    val postContent: String,
    val postContentImageUrl: String,
    val statistics: List<StatisticItem>,
)