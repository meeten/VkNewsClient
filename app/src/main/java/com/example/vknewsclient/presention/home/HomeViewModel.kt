package com.example.vknewsclient.presention.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.FeedPost

class HomeViewModel : ViewModel() {
    private val initList = mutableListOf<FeedPost>().apply {
        repeat(100) {
            add(FeedPost(id = it))
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPost>>(initList)
    val feedPosts: LiveData<List<FeedPost>> get() = _feedPosts

    fun deleteFeedPost(feedPost: FeedPost) {
        val modifiedFeedPosts = _feedPosts.value?.toMutableList() ?: mutableListOf()
        modifiedFeedPosts.remove(feedPost)

        _feedPosts.value = modifiedFeedPosts
    }
}