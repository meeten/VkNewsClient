package com.example.vknewsclient.presention.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vknewsclient.domain.models.FeedPost

class CommentViewModelFactory(private val feedPost: FeedPost) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(feedPost) as T
        }

        throw IllegalStateException("unknown View Model: $modelClass")
    }
}