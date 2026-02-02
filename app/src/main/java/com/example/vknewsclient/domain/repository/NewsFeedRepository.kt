package com.example.vknewsclient.domain.repository

import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    val data: StateFlow<List<FeedPost>>

    val tokenValidEvents: SharedFlow<Unit>

    suspend fun loadNextData()

    suspend fun hidePostFromNewsFeed(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)

    suspend fun resetAndLoadData()

    fun getComments(feedPost: FeedPost): Flow<List<CommentItem>>

}