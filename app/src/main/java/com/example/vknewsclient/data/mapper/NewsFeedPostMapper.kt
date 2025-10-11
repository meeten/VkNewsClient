package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.data.model.NewsFeedResponseDto
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
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
                publicationTime = post.date,
                postContent = post.text,
                postContentImageUrl = post.attachmentsDto?.lastOrNull()?.photo?.photoUrls?.lastOrNull()?.photoUrl,
                statistics = listOf<StatisticItem>(
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
                    ),
                    StatisticItem(
                        type = StatisticItemType.VIEWS,
                        count = post.viewsDto.count
                    )
                )
            )

            result.add(feedPost)
        }

        return result
    }
}