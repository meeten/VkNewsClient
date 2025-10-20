package com.example.vknewsclient.presention.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.repository.CommentsRepository
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.state.CommentScreenState
import kotlinx.coroutines.launch

class CommentViewModel(val feedPost: FeedPost) : ViewModel() {

    private val repository = CommentsRepository
    private val _commentScreenState = MutableLiveData<CommentScreenState>()
    val commentScreenState: LiveData<CommentScreenState> get() = _commentScreenState

    init {
        loadComment()
    }

    fun loadComment() {
        viewModelScope.launch {
            val comments = repository.getComments(feedPost)
            _commentScreenState.value = CommentScreenState.Comments(comments, feedPost)
        }
    }
}