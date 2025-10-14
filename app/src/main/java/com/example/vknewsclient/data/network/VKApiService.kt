package com.example.vknewsclient.data.network

import com.example.vknewsclient.data.model.LikesResponseDto
import com.example.vknewsclient.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VKApiService {
    @GET("wall.get?v=5.199&extended=true&count=50")
    suspend fun loadPostsByDomain(
        @Query("access_token") accessToken: String,
        @Query("domain") domain: String,
    ): NewsFeedResponseDto

    @GET("likes.add?v=5.199&type=post")
    suspend fun addLike(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Int,
        @Query("item_id") itemId: Int,
    ): LikesResponseDto

    @GET("likes.delete?v=5.199&type=post")
    suspend fun deleteLike(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Int,
        @Query("item_id") itemId: Int,
    ): LikesResponseDto
}