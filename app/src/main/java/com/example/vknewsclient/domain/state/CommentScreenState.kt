package com.example.vknewsclient.domain.state

import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost

sealed class CommentScreenState {
    object Initial : CommentScreenState()

    data class Comments(
        val comments: List<CommentItem>,
        val feedPost: FeedPost,
    ) : CommentScreenState()
}