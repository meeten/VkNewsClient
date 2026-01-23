package com.example.vknewsclient.presention.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.repository.NewsFeedRepository
import com.example.vknewsclient.domain.state.RootState
import com.vk.id.VKID
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val vkid = VKID.instance
    private val _rootState = MutableLiveData<RootState>()
    val rootState: LiveData<RootState> get() = _rootState

    init {
        Log.d("MainViewModel", "init")
        viewModelScope.launch {
            Log.d("MainViewModel", "collect")
            NewsFeedRepository.tokenValidEvents.collect {
                _rootState.value = RootState.Unauthorized
            }
        }

        checkAuth()
    }

    fun onAuthorized() {
        _rootState.value = RootState.Authorized
    }

    private fun checkAuth() {
        val token = vkid.accessToken

        Log.d("MainViewModel", "expireTime: ${token?.expireTime}")
        if (token != null && token.expireTime != 0L) {
            _rootState.value = RootState.Authorized
        } else {
            _rootState.value = RootState.Unauthorized
        }
    }
}