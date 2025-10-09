package com.example.vknewsclient.domain.models

import com.example.vknewsclient.R
import kotlin.random.Random
import kotlin.random.nextInt

private fun randomCountInRangeForStatisticItem(range: IntRange): Int {
    return Random.nextInt(range)
}

data class FeedPost(
    val id: String,
    val publicImageUrl: String,
    val publicName: String,
    val publicationTime: String,
    val postContent: String,
    val postContentImageUrl: String,
    val statistics: List<StatisticItem>,
)