package com.example.vknewsclient.presention.comment

import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.presention.comment.CommentScreenState
import com.example.vknewsclient.domain.GetCommentsUseCase
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentViewModel @Inject constructor(
    getCommentsUseCase: GetCommentsUseCase,
    val feedPost: FeedPost,
) : ViewModel() {

    val commentState = getCommentsUseCase(feedPost)
        .filter { it.isNotEmpty() }
        .map { CommentScreenState.Comments(it, feedPost) as CommentScreenState }
}