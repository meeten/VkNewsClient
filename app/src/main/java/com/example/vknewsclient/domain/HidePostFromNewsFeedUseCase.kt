package com.example.vknewsclient.domain

import com.example.vknewsclient.domain.models.FeedPost
import javax.inject.Inject

class HidePostFromNewsFeedUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.hidePostFromNewsFeed(feedPost)
    }
}