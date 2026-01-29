package com.example.vknewsclient.presention.auth

sealed class AuthState {

    object Loading : AuthState()
    object NoAuthenticated : AuthState()
    object Authenticated : AuthState()
    data class Error(val failDescription: String) : AuthState()
}