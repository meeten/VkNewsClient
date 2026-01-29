package com.example.vknewsclient.di

import com.example.vknewsclient.data.repository.AuthRepositoryImpl
import com.example.vknewsclient.data.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.AuthRepository
import com.example.vknewsclient.domain.NewsFeedRepository
import com.example.vknewsclient.domain.TimeConverter
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindNewsFeedRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository
}