package com.example.vknewsclient.presention.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.domain.AuthStatus
import com.example.vknewsclient.domain.AuthorizedUseCase
import com.example.vknewsclient.presention.auth.AuthState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authorizedUseCase: AuthorizedUseCase
) : ViewModel() {

    private val _authState =
        MutableStateFlow<AuthState>(AuthState.NoAuthenticated)
    val authState
        get() = _authState.asStateFlow()

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            _authState.value = AuthState.Error(throwable.message.toString())
        }

    fun registration() {
        _authState.value = AuthState.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            val authStatus = authorizedUseCase()
            when (authStatus) {
                AuthStatus.UNKNOWN -> {}

                AuthStatus.AUTHORIZED -> {
                    _authState.value = AuthState.Authenticated
                }

                AuthStatus.UNAUTHORIZED -> {
                    _authState.value = AuthState.NoAuthenticated
                }
            }
        }
    }
}