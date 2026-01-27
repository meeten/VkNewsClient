package com.example.vknewsclient.presention.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.repository.NewsFeedRepository
import com.example.vknewsclient.domain.state.RootState
import com.vk.id.VKID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val vkid = VKID.instance

    private val _rootState = MutableStateFlow<RootState>(
        RootState.Initial
    )
    val rootState get() = _rootState.asSharedFlow()

    init {
        checkAuth()
        observeTokenEvents()
    }

    fun onAuthorized() {
        viewModelScope.launch {
            _rootState.value = RootState.Authorized
        }
    }

    private fun checkAuth() {
        viewModelScope.launch {
            val token = vkid.accessToken
            val state = if (token != null && token.expireTime != 0L) {
                RootState.Authorized
            } else {
                RootState.Unauthorized
            }
            _rootState.value = state
        }
    }

    private fun observeTokenEvents() {
        viewModelScope.launch {
            NewsFeedRepository.tokenValidEvents.collect {
                _rootState.value = RootState.Unauthorized
            }
        }
    }
}