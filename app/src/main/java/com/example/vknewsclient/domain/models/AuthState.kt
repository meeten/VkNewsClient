package com.example.vknewsclient.domain.models

import com.vk.id.AccessToken

sealed class AuthState {
    object Loading : AuthState()
    object NoAuthenticated : AuthState()
    data class Authenticated(val accessToken: AccessToken) : AuthState()
    data class Error(val failDescription: String) : AuthState()
}