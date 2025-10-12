package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.data.model.NewsFeedResponseDto
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.absoluteValue

class NewsFeedPostMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {

            val group = groups.find { it.id == post.fromId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                publicImageUrl = group.photoGroupUrl,
                publicName = group.name,
                publicationTime = convertTimestampInDate(post.date),
                postContent = post.text,
                postContentImageUrl = post.attachmentsDto?.lastOrNull()?.photo?.photoUrls?.lastOrNull()?.photoUrl
                    ?: continue,
                statistics = listOf<StatisticItem>(
                    StatisticItem(
                        type = StatisticItemType.VIEWS,
                        count = post.viewsDto.count
                    ),
                    StatisticItem(
                        type = StatisticItemType.LIKES,
                        count = post.likesDto.count
                    ),
                    StatisticItem(
                        type = StatisticItemType.COMMENTS,
                        count = post.commentsDto.count
                    ),
                    StatisticItem(
                        type = StatisticItemType.SHARES,
                        count = post.repostsDto.count
                    )
                )
            )

            result.add(feedPost)
        }

        return result
    }
}

private fun convertTimestampInDate(timestamp: Int): String {
    val date = Date(timestamp * 1000L)
    val format = SimpleDateFormat("d MMM HH:mm")
    return format.format(date).replace(".", "")
}