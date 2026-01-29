package com.example.vknewsclient.di

import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.data.network.VKApiService
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideApiService(): VKApiService {
        return ApiFactory.apiService
    }
}