package com.example.vknewsclient.domain

import com.example.vknewsclient.domain.models.FeedPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.data
    }
}