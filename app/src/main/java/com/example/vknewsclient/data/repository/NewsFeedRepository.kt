package com.example.vknewsclient.data.repository

import android.util.Log
import com.example.vknewsclient.R
import com.example.vknewsclient.data.mapper.NewsFeedPostMapper
import com.example.vknewsclient.data.model.LikesResponseDto
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import com.vk.id.VKID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object NewsFeedRepository {

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts get() = _feedPosts.toList()

    private val apiFactory = ApiFactory
    private val mapper = NewsFeedPostMapper()


    var nextFrom: String? = null
    suspend fun loadNewsFeed(): List<FeedPost> {
        if (nextFrom == null && _feedPosts.isNotEmpty()) return feedPosts

        val startFrom = nextFrom

        val newsFeedResponseDto = if (startFrom == null) {
            apiFactory.apiService.loadPosts(
                accessToken = getAccessToken()
            )
        } else {
            apiFactory.apiService.loadPosts(
                accessToken = getAccessToken(),
                startFrom = startFrom
            )
        }

        nextFrom = newsFeedResponseDto.newsFeedContent.nextFrom

        val newsFeed = mapper.mapResponseToPosts(newsFeedResponseDto)

        _feedPosts.addAll(newsFeed)
        return feedPosts
    }

    private fun getAccessToken(): String {
        return VKID.instance.accessToken?.token
            ?: throw IllegalAccessError("Token was not received")
    }

    fun hidePostFromNewsFeed(feedPost: FeedPost) {
        _feedPosts.remove(feedPost)

        // Сетевой запрос (не ждем)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                apiFactory.apiService.hidePostFromNewsFeed(
                    accessToken = getAccessToken(),
                    ownerId = feedPost.ownerId,
                    itemId = feedPost.id
                )
            } catch (e: Exception) {
                Log.e("HIDE_POST", "Failed to hide post", e)
            }
        }
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