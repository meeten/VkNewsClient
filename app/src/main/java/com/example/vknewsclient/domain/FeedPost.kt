package com.example.vknewsclient.domain

import com.example.vknewsclient.R
import kotlin.random.Random
import kotlin.random.nextInt

private fun randomCountInRangeForStatisticItem(range: IntRange): Int {
    return Random.nextInt(range)
}

data class FeedPost(
    val id: Int,
    val publicImage: Int = R.drawable.post_comunity_thumbnail,
    val publicName: String = "/dev/null",
    val publicationTime: String = "14:00",
    val postContent: String = "content content content content content",
    val postContentImage: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf<StatisticItem>(
        StatisticItem(
            type = StatisticItemType.LIKES,
            count = randomCountInRangeForStatisticItem(10..100)
        ),
        StatisticItem(
            type = StatisticItemType.COMMENTS,
            count = randomCountInRangeForStatisticItem(10..100)
        ),
        StatisticItem(
            type = StatisticItemType.SHARES,
            count = randomCountInRangeForStatisticItem(10..100)
        ),
        StatisticItem(
            type = StatisticItemType.VIEWS,
            count = randomCountInRangeForStatisticItem(100..1000)
        )
    ),
)
