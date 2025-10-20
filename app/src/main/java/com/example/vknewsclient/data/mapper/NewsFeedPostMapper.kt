package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.R
import com.example.vknewsclient.data.model.NewsFeedResponseDto
import com.example.vknewsclient.domain.TimeConverter
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import kotlin.math.absoluteValue

class NewsFeedPostMapper {
    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups
        val timeConverter = TimeConverter()

        for (post in posts) {

            val group = groups.find { it.id == post.ownerId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                ownerId = post.ownerId,
                publicImageUrl = group.photoGroupUrl,
                publicName = group.name,
                publicationTime = timeConverter.convertTimestampInDate(post.date),
                postContent = post.text,
                postContentImageUrl = post.attachmentsDto?.lastOrNull()?.photo?.photoUrls?.lastOrNull()?.photoUrl
                    ?: continue,
                isLiked = post.likesDto.isLiked == 1,
                statistics = listOf<StatisticItem>(
                    StatisticItem(
                        type = StatisticItemType.VIEWS,
                        src = R.drawable.ic_views_count,
                        count = post.viewsDto.count
                    ),
                    StatisticItem(
                        type = StatisticItemType.LIKES,
                        src = if (post.likesDto.isLiked == 1) R.drawable.ic_like_set else R.drawable.ic_like,
                        count = post.likesDto.count
                    ),
                    StatisticItem(
                        type = StatisticItemType.COMMENTS,
                        src = R.drawable.ic_comment,
                        count = post.commentsDto.count
                    ),
                    StatisticItem(
                        type = StatisticItemType.SHARES,
                        src = R.drawable.ic_share,
                        count = post.repostsDto.count
                    )
                )
            )

            result.add(feedPost)
        }

        return result
    }
}