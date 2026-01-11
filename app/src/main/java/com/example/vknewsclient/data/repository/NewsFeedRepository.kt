package com.example.vknewsclient.data.repository

import android.util.Log
import com.example.vknewsclient.R
import com.example.vknewsclient.data.mapper.NewsFeedPostMapper
import com.example.vknewsclient.data.model.LikesResponseDto
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import com.example.vknewsclient.extensions.mergeWith
import com.vk.id.VKID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

object NewsFeedRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
    private val apiFactory = ApiFactory
    private val mapper = NewsFeedPostMapper()

    private val loadedListFlow = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            val startFrom = nextFrom

            if (startFrom == null && feedPosts.isNotEmpty()) {
                emit(feedPosts.toList())
                return@collect
            }

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
            emit(feedPosts.toList())
        }
    }
    private val refreshListFlow = MutableSharedFlow<List<FeedPost>>()
    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost> get() = _feedPosts.toList()

    private var nextFrom: String? = null
    val data: StateFlow<List<FeedPost>> =
        loadedListFlow
            .mergeWith(refreshListFlow)
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.Lazily,
                initialValue = feedPosts
            )

    suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }

    suspend fun hidePostFromNewsFeed(feedPost: FeedPost) {
        _feedPosts.remove(feedPost)

        // Сетевой запрос (не ждем)
        try {
            apiFactory.apiService.hidePostFromNewsFeed(
                accessToken = getAccessToken(),
                ownerId = feedPost.ownerId,
                itemId = feedPost.id
            )
        } catch (e: Exception) {
            Log.e("HIDE_POST", "Failed to hide post", e)
        }

        refreshListFlow.emit(feedPosts.toList())
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
        refreshListFlow.emit(feedPosts.toList())
    }

    private suspend fun getNewCount(feedPost: FeedPost): Int {
        val likesResponseDto =
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

    private fun getAccessToken(): String {
        return VKID.instance.accessToken?.token
            ?: throw IllegalAccessError("Token was not received")
    }
}