package com.example.vknewsclient.data.repository

import com.example.vknewsclient.data.mapper.CommentItemMapper
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.domain.models.FeedPost
import com.vk.id.VKID
import kotlinx.coroutines.flow.flow

object CommentsRepository {

    private val apiFactory = ApiFactory
    private val mapper = CommentItemMapper()

    fun getComments(feedPost: FeedPost) = flow {
        val commentsResponseDto = apiFactory.apiService.getComments(
            accessToken = getAccessToken(),
            ownerId = feedPost.ownerId,
            postId = feedPost.id
        )

        val comments =
            mapper.mapResponseToComments(commentsResponseDto)
        emit(comments)
    }

    private fun getAccessToken(): String {
        return VKID.instance.accessToken?.token
            ?: throw IllegalAccessError("Token was not received")
    }
}