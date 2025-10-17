package com.example.vknewsclient.data.network

import com.example.vknewsclient.data.model.IgnoreFeedPostResponseDto
import com.example.vknewsclient.data.model.LikesResponseDto
import com.example.vknewsclient.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VKApiService {

    @GET("newsfeed.get?v=5.199&filters=post")
    suspend fun loadPosts(
        @Query("access_token") accessToken: String,
    ): NewsFeedResponseDto

    @GET("newsfeed.get?v=5.199&filters=post")
    suspend fun loadPosts(
        @Query("access_token") accessToken: String,
        @Query("start_from") startFrom: String,
    ): NewsFeedResponseDto

    @GET("likes.add?v=5.199&type=post")
    suspend fun addLike(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") itemId: Long,
    ): LikesResponseDto

    @GET("likes.delete?v=5.199&type=post")
    suspend fun deleteLike(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") itemId: Long,
    ): LikesResponseDto

    @GET("newsfeed.ignoreItem?v=5.199&type=wall")
    suspend fun hidePostFromNewsFeed(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") itemId: Long,
    ): IgnoreFeedPostResponseDto
}