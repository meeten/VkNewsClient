package com.example.vknewsclient.presention.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.repository.NewsFeedRepository
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.state.NewsFeedScreenState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = NewsFeedRepository
    private val _newsFeedScreenState =
        MutableLiveData<NewsFeedScreenState>(NewsFeedScreenState.Initial)
    val newsFeedScreenState: LiveData<NewsFeedScreenState> get() = _newsFeedScreenState

    init {
        loadPostsByDomain()
    }

    fun loadPostsByDomain() {
        viewModelScope.launch {
            val newsFeed = repository.loadNewsFeed()
            _newsFeedScreenState.value = NewsFeedScreenState.Posts(newsFeed)
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _newsFeedScreenState.value = NewsFeedScreenState.Posts(repository.feedPosts)
        }
    }

    fun incrementStatistics(feedPost: FeedPost, statisticItem: StatisticItem) {
        updateFeedPost(feedPost) { oldFeedPost ->
            val newStatistics = updateCountStatisticItem(oldFeedPost.statistics, statisticItem)
            oldFeedPost.copy(statistics = newStatistics)
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

    private fun updateFeedPost(feedPost: FeedPost, update: (FeedPost) -> FeedPost) {
        var currentState = _newsFeedScreenState.value
        if (currentState is NewsFeedScreenState.Posts) {
            val modifierFeedPosts = currentState.feedPosts.toMutableList()
            modifierFeedPosts.replaceAll { oldFeedPost ->
                if (oldFeedPost.id == feedPost.id) update(oldFeedPost) else oldFeedPost
            }

            currentState = currentState.copy(feedPosts = modifierFeedPosts)
            _newsFeedScreenState.value = currentState
        }
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