package com.example.vknewsclient.presention.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.domain.AuthStatus
import com.example.vknewsclient.domain.CheckAuthStatusUseCase
import com.example.vknewsclient.domain.GetTokenValidEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase,
    private val getTokenValidEventsUseCase: GetTokenValidEventsUseCase
) : ViewModel() {

    private val _rootState = MutableStateFlow<RootState>(
        RootState.Initial
    )
    val rootState get() = _rootState.asStateFlow()

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
        val authStatus = checkAuthStatusUseCase()
        when (authStatus) {
            AuthStatus.UNKNOWN -> {
                _rootState.value = RootState.Initial
            }

            AuthStatus.AUTHORIZED -> {
                Log.d("MainViewModel", "Authorized")
                _rootState.value = RootState.Authorized
            }

            AuthStatus.UNAUTHORIZED -> {
                _rootState.value = RootState.Unauthorized
            }
        }
    }

    private fun observeTokenEvents() {
        val tokenEvents = getTokenValidEventsUseCase()
        viewModelScope.launch {
            tokenEvents.collect {
                _rootState.value = RootState.Unauthorized
            }
        }
    }
}