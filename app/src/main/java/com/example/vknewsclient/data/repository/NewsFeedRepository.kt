package com.example.vknewsclient.data.repository

import com.example.vknewsclient.data.mapper.NewsFeedPostMapper
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.domain.models.FeedPost
import com.vk.id.VKID

object NewsFeedRepository {

    private val apiFactory = ApiFactory
    private val mapper = NewsFeedPostMapper()

    suspend fun loadNewsFeed(): List<FeedPost> {
        val newsFeedResponseDto = apiFactory.apiService.loadPostsByDomain(
            accessToken = getAccessToken(),
            domain = "rhymes"
        )
        val newsFeed = mapper.mapResponseToPosts(newsFeedResponseDto)

        return newsFeed
    }

    private fun getAccessToken(): String {
        return VKID.instance.accessToken?.token
            ?: throw IllegalAccessError("Token was not received")
    }
}