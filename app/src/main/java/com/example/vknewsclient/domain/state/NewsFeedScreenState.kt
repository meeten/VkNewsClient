package com.example.vknewsclient.domain.state

import com.example.vknewsclient.domain.models.FeedPost

sealed class NewsFeedScreenState {
    object Initial : NewsFeedScreenState()

    object Loading : NewsFeedScreenState()

    data class Posts(
        val feedPosts: List<FeedPost>,
        val isLoadingNextPosts: Boolean = false,
    ) : NewsFeedScreenState()
}