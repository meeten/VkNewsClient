package com.example.vknewsclient.domain.usecase

import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    operator fun invoke(feedPost: FeedPost): Flow<List<CommentItem>> {
        return repository.getComments(feedPost)
    }
}