package com.example.vknewsclient.presention.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.domain.ChangeLikeStatusUseCase
import com.example.vknewsclient.domain.DataUseCase
import com.example.vknewsclient.domain.HidePostFromNewsFeedUseCase
import com.example.vknewsclient.domain.LoadNextDataUseCase
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    dataUseCase: DataUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val hidePostFromNewsFeedUseCase: HidePostFromNewsFeedUseCase
) : ViewModel() {

    private val dataFlow = dataUseCase()

    private val exceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            Log.d("HomeViewModel", "Handler caught error")
        }

    private val nextDataNeeded =
        MutableSharedFlow<Unit>()
    private val nextDataFlow = flow {
        nextDataNeeded.collect {
            emit(NewsFeedScreenState.Posts(dataFlow.value, true))
        }
    }

    val newsFeedScreenState = dataFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(nextDataFlow)

    fun loadNextPosts() {
        viewModelScope.launch {
            nextDataNeeded.emit(Unit)
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)
        }
    }

    fun hidePostFromNewsFeed(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            hidePostFromNewsFeedUseCase(feedPost)
        }
    }
}