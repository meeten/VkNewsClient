package com.example.vknewsclient.data.repository

import com.example.vknewsclient.data.mapper.CommentItemMapper
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost
import com.vk.id.VKID

object CommentsRepository {
    private val _comments = mutableListOf<CommentItem>()
    val comments get() = _comments.toList()

    private val mapper = CommentItemMapper()

    private val apiFactory = ApiFactory

    suspend fun getComments(feedPost: FeedPost): List<CommentItem> {
        val commentsResponseDto = apiFactory.apiService.getComments(
            accessToken = getAccessToken(),
            ownerId = feedPost.ownerId,
            postId = feedPost.id
        )

        val newComments = mapper.mapResponseToComments(commentsResponseDto)
        _comments.addAll(newComments)
        return newComments
    }

    private fun getAccessToken(): String {
        return VKID.instance.accessToken?.token
            ?: throw IllegalAccessError("Token was not received")
    }
}