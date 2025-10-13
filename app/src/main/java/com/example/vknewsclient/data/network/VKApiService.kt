package com.example.vknewsclient.data.network

import com.example.vknewsclient.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VKApiService {
    @GET("wall.get?v=5.199&extended=true&count=50")
    suspend fun loadPostsByDomain(
        @Query("access_token") accessToken: String,
        @Query("domain") domain: String,
    ): NewsFeedResponseDto
}