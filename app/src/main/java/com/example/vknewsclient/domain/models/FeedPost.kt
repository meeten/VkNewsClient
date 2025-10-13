package com.example.vknewsclient.domain.models

data class FeedPost(
    val id: Int,
    val publicImageUrl: String,
    val publicName: String,
    val publicationTime: String,
    val postContent: String,
    val postContentImageUrl: String,
    val isFavorite: Boolean,
    val statistics: List<StatisticItem>,
)