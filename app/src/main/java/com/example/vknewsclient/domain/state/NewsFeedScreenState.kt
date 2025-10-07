package com.example.vknewsclient.domain.state

import com.example.vknewsclient.domain.models.FeedPost

sealed class NewsFeedScreenState {
    object Initial : NewsFeedScreenState()
    data class Posts(val feedPosts: List<FeedPost>) : NewsFeedScreenState()
}