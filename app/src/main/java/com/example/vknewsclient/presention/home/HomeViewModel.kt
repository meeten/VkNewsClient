package com.example.vknewsclient.presention.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.repository.NewsFeedRepository
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.state.NewsFeedScreenState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = NewsFeedRepository
    private val _newsFeedScreenState =
        MutableLiveData<NewsFeedScreenState>(NewsFeedScreenState.Initial)
    val newsFeedScreenState: LiveData<NewsFeedScreenState> get() = _newsFeedScreenState

    init {
        _newsFeedScreenState.value = NewsFeedScreenState.Loading
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            val newsFeed = repository.loadNewsFeed()
            _newsFeedScreenState.value = NewsFeedScreenState.Posts(newsFeed)
        }
    }

    fun loadNextPosts() {
        _newsFeedScreenState.value =
            NewsFeedScreenState.Posts(repository.feedPosts, true)

        loadPosts()
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _newsFeedScreenState.value = NewsFeedScreenState.Posts(repository.feedPosts)
        }
    }

    fun hidePostFromNewsFeed(feedPost: FeedPost) {
        repository.hidePostFromNewsFeed(feedPost)
        _newsFeedScreenState.value = NewsFeedScreenState.Posts(feedPosts = repository.feedPosts)
    }
}