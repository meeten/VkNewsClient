package com.example.vknewsclient.presention.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.StatisticItem

class HomeViewModel : ViewModel() {
    private val initList = mutableListOf<FeedPost>().apply {
        repeat(100) {
            add(FeedPost(id = it))
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPost>>(initList)
    val feedPosts: LiveData<List<FeedPost>> get() = _feedPosts

    fun changeStatisticsFeedPost(
        feedPost: FeedPost,
        statisticItem: StatisticItem,
    ) {
        val modifiedFeedPosts = _feedPosts.value?.toMutableList() ?: mutableListOf()
        modifiedFeedPosts.replaceAll { oldFeedPost ->
            if (oldFeedPost.id == feedPost.id) {
                val newStatistics =
                    updateCountStatisticItem(feedPost.statistics, statisticItem)
                oldFeedPost.copy(statistics = newStatistics)
            } else {
                oldFeedPost
            }
        }

        _feedPosts.value = modifiedFeedPosts
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
        val modifiedFeedPosts = _feedPosts.value?.toMutableList() ?: mutableListOf()
        modifiedFeedPosts.remove(feedPost)

        _feedPosts.value = modifiedFeedPosts
    }
}