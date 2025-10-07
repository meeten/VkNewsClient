package com.example.vknewsclient.presention.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.state.CommentScreenState

class CommentViewModel(val feedPost: FeedPost) : ViewModel() {
    private val _commentScreenState = MutableLiveData<CommentScreenState>()
    val commentScreenState: LiveData<CommentScreenState> get() = _commentScreenState

    init {
        loadComment()
    }

    fun loadComment() {
        val initComments = mutableListOf<CommentItem>().apply {
            repeat(100) {
                add(CommentItem(id = it))
            }
        }

        _commentScreenState.value = CommentScreenState.Comments(initComments, feedPost)
    }
}