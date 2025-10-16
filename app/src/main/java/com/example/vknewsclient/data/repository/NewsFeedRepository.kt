package com.example.vknewsclient.data.repository

import com.example.vknewsclient.R
import com.example.vknewsclient.data.mapper.NewsFeedPostMapper
import com.example.vknewsclient.data.model.LikesResponseDto
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import com.vk.id.VKID

object NewsFeedRepository {

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts get() = _feedPosts.toList()

    private val apiFactory = ApiFactory
    private val mapper = NewsFeedPostMapper()

    suspend fun loadNewsFeed(): List<FeedPost> {
        val newsFeedResponseDto = apiFactory.apiService.loadPosts(
            accessToken = getAccessToken()
        )
        val newsFeed = mapper.mapResponseToPosts(newsFeedResponseDto)

        _feedPosts.addAll(newsFeed)
        return newsFeed
    }

    private fun getAccessToken(): String {
        return VKID.instance.accessToken?.token
            ?: throw IllegalAccessError("Token was not received")
    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val indexFeedPost = _feedPosts.indexOf(feedPost)
        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf {
                it.type == StatisticItemType.LIKES
            }
            add(
                StatisticItem(
                    type = StatisticItemType.LIKES,
                    src = if (feedPost.isLiked) R.drawable.ic_like else R.drawable.ic_like_set,
                    count = getNewCount(feedPost)
                )
            )
        }

        val newFeedPost = feedPost.copy(
            isLiked = !feedPost.isLiked, statistics = newStatistics
        )
        _feedPosts[indexFeedPost] = newFeedPost
    }

    private suspend fun getNewCount(feedPost: FeedPost): Int {
        var likesResponseDto: LikesResponseDto? = null
        likesResponseDto =
            if (feedPost.isLiked)
                ApiFactory.apiService.deleteLike(
                    getAccessToken(),
                    feedPost.ownerId,
                    feedPost.id
                )
            else
                ApiFactory.apiService.addLike(
                    getAccessToken(),
                    feedPost.ownerId,
                    feedPost.id
                )

        return likesResponseDto.likes.count
    }
}