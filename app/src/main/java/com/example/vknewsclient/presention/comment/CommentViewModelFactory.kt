package com.example.vknewsclient.presention.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class CommentViewModelFactory @Inject constructor(
    private val viewModel: Provider<CommentViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }
}