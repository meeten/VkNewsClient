package com.example.vknewsclient.presention.comment

import androidx.lifecycle.ViewModel
import com.example.vknewsclient.data.repository.NewsFeedRepository
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.state.CommentScreenState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class CommentViewModel(val feedPost: FeedPost) : ViewModel() {

    private val repository = NewsFeedRepository

    val commentState = repository.getComments(feedPost)
        .filter { it.isNotEmpty() }
        .map { CommentScreenState.Comments(it, feedPost) as CommentScreenState }
}