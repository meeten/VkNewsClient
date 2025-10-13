package com.example.vknewsclient.presention.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.R
import com.example.vknewsclient.data.mapper.NewsFeedPostMapper
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType
import com.example.vknewsclient.domain.state.NewsFeedScreenState
import com.vk.id.VKID
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val initState = NewsFeedScreenState.Initial
    private val _newsFeedScreenState = MutableLiveData<NewsFeedScreenState>(initState)
    val newsFeedScreenState: LiveData<NewsFeedScreenState> get() = _newsFeedScreenState

    init {
        loadPostsByDomain()
    }

    private val mapper = NewsFeedPostMapper()
    fun loadPostsByDomain() {
        viewModelScope.launch {
            val token = VKID.instance.accessToken?.token ?: return@launch
            val domain = "rhymes"
            val responseDto = ApiFactory.apiService.loadPostsByDomain(token, domain)

            val newsFeed = mapper.mapResponseToPosts(responseDto)
            _newsFeedScreenState.value = NewsFeedScreenState.Posts(newsFeed)
        }
    }

    fun changeStatisticsFeedPost(feedPost: FeedPost) {
        var currentState = _newsFeedScreenState.value
        if (currentState is NewsFeedScreenState.Posts) {
            val modifierFeedPosts = currentState.feedPosts.toMutableList()
            modifierFeedPosts.replaceAll { oldFeedPost ->
                if (oldFeedPost.id == feedPost.id) {
                    val newStatistics =
                        updateBackgroundImageLike(feedPost.statistics, feedPost.isFavorite)
                    oldFeedPost.copy(isFavorite = !feedPost.isFavorite, statistics = newStatistics)
                } else {
                    oldFeedPost
                }
            }

            currentState = currentState.copy(feedPosts = modifierFeedPosts)
            _newsFeedScreenState.value = currentState
        }
    }

    private fun updateBackgroundImageLike(
        statistics: List<StatisticItem>,
        isFavorite: Boolean,
    ): List<StatisticItem> {
        val modifiedStatistics = statistics.toMutableList()
        modifiedStatistics.replaceAll { oldStatistics ->
            if (oldStatistics.type == StatisticItemType.LIKES) {
                oldStatistics.copy(
                    src =
                        if (isFavorite) R.drawable.ic_like else R.drawable.ic_like_set,
                    count =
                        if (isFavorite) oldStatistics.count - 1 else oldStatistics.count + 1
                )
            } else {
                oldStatistics
            }
        }

        return modifiedStatistics
    }

    fun changeStatisticsFeedPost(
        feedPost: FeedPost,
        statisticItem: StatisticItem,
    ) {
        var currentState = _newsFeedScreenState.value
        if (currentState is NewsFeedScreenState.Posts) {
            val modifiedFeedPosts = currentState.feedPosts.toMutableList()
            modifiedFeedPosts.replaceAll { oldFeedPost ->
                if (oldFeedPost.id == feedPost.id) {
                    val newStatistics =
                        updateCountStatisticItem(feedPost.statistics, statisticItem)
                    oldFeedPost.copy(statistics = newStatistics)
                } else {
                    oldFeedPost
                }
            }

            currentState = currentState.copy(feedPosts = modifiedFeedPosts)
            _newsFeedScreenState.value = currentState
        }

    }

    private fun updateCountStatisticItem(
        statistics: List<StatisticItem>,
        statisticItem: StatisticItem,
    ): List<StatisticItem> {
        val modifiedStatistic = statistics.toMutableList()
        modifiedStatistic.replaceAll { oldStatisticItem ->
            if (oldStatisticItem.type == statisticItem.type) {
                oldStatisticItem.copy(count = oldStatisticItem.count + 1)
            } else {
                oldStatisticItem
            }
        }

        return modifiedStatistic
    }

    fun deleteFeedPost(feedPost: FeedPost) {
        var currentState = _newsFeedScreenState.value
        if (currentState is NewsFeedScreenState.Posts) {
            val modifiedFeedPosts = currentState.feedPosts.toMutableList()
            modifiedFeedPosts.remove(feedPost)

            currentState = currentState.copy(feedPosts = modifiedFeedPosts)
            _newsFeedScreenState.value = currentState
        }
    }
}