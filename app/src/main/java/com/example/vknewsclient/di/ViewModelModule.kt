package com.example.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.example.vknewsclient.presention.auth.AuthViewModel
import com.example.vknewsclient.presention.home.HomeViewModel
import com.example.vknewsclient.presention.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(impl: AuthViewModel): ViewModel

    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @Binds
    fun bindHomeViewModel(impl: HomeViewModel): ViewModel
}