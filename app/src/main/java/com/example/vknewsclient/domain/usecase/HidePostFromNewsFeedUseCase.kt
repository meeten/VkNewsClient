package com.example.vknewsclient.domain.usecase

import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class HidePostFromNewsFeedUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.hidePostFromNewsFeed(feedPost)
    }
}