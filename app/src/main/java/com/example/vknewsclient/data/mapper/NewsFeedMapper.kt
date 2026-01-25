package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.data.model.CommentsResponseDto
import com.example.vknewsclient.data.model.NewsFeedResponseDto
import com.example.vknewsclient.domain.TimeConverter
import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.Profile
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import kotlin.math.absoluteValue

class NewsFeedMapper {

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

    fun mapResponseToComments(response: CommentsResponseDto): List<CommentItem> {
        val result = mutableListOf<CommentItem>()

        val comments = response.commentsContentDto.comments
        val profiles = response.commentsContentDto.profiles
        val timeConverter = TimeConverter()

        for (commentItemDto in comments) {
            val profile =
                profiles.find { it.id == commentItemDto.profileId.absoluteValue } ?: continue

            val commentItem = CommentItem(
                id = commentItemDto.id,
                text = commentItemDto.text,
                publicationTime = timeConverter.convertTimestampInDate(commentItemDto.dateUrl),
                profile = Profile(
                    id = profile.id,
                    firstName = profile.firstName,
                    lastName = profile.lastName,
                    profilePhoto100Url = profile.profilePhoto100Url
                )
            )

            result.add(commentItem)
        }

        return result
    }

}