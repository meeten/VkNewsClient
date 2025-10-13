package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.R
import com.example.vknewsclient.data.model.NewsFeedResponseDto
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import java.text.SimpleDateFormat
import java.time.LocalDateTime
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
                isFavorite = post.isFavorite,
                statistics = listOf<StatisticItem>(
                    StatisticItem(
                        type = StatisticItemType.VIEWS,
                        src = R.drawable.ic_views_count,
                        count = post.viewsDto.count
                    ),
                    StatisticItem(
                        type = StatisticItemType.LIKES,
                        src = if (post.isFavorite) R.drawable.ic_like_set else R.drawable.ic_like,
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

    private val specialTimeInTimestamp = getSpecialDateInTimestamp()
    private fun convertTimestampInDate(timestamp: Int): String {
        val timeDifference = specialTimeInTimestamp - timestamp

        val date = Date(timestamp * 1000L)
        return when {
            timeDifference < 60 -> {
                "только что"
            }

            timeDifference < 86400 -> {
                val format = SimpleDateFormat("HH:mm")
                "сегодня в ${format.format(date)}"
            }

            timeDifference < 2 * 86400 -> {
                val format = SimpleDateFormat("HH:mm")
                "вчера в ${format.format(date)}"
            }

            else -> {
                val format = SimpleDateFormat("d MMM HH:mm")
                return format.format(Date(timestamp * 1000L)).replace(".", " в")
            }
        }
    }

    private fun getSpecialDateInTimestamp(): Long {
        val specialDate = LocalDateTime.now().plusDays(1)
        val year = specialDate.year
        val month = specialDate.monthValue
        val day = specialDate.dayOfMonth
        val specialPatternDate = "$year-$month-$day 00:00:00"
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specialPatternDate).time / 1000L
    }
}