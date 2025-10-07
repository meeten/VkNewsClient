package com.example.vknewsclient.presention.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.domain.state.AuthState
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.refresh.VKIDRefreshTokenCallback
import com.vk.id.refresh.VKIDRefreshTokenFail
import com.vk.id.refresh.VKIDRefreshTokenFail.FailedApiCall
import com.vk.id.refresh.VKIDRefreshTokenFail.FailedOAuthState
import com.vk.id.refresh.VKIDRefreshTokenFail.NotAuthenticated
import com.vk.id.refresh.VKIDRefreshTokenFail.RefreshTokenExpired
import kotlinx.coroutines.launch

class AuthViewModel(val vkid: VKID) : ViewModel() {
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> get() = _authState

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch {
            vkid.refreshToken(callback = object : VKIDRefreshTokenCallback {
                override fun onSuccess(token: AccessToken) {
                    _authState.value = AuthState.Authenticated(token)
                }

                override fun onFail(fail: VKIDRefreshTokenFail) {
                    when (fail) {
                        is FailedApiCall -> {
                            _authState.value =
                                AuthState.Error(fail.description)
                        }

                        is FailedOAuthState -> {
                            _authState.value =
                                AuthState.Error(fail.description)
                        }

                        is RefreshTokenExpired -> {
                            _authState.value =
                                AuthState.NoAuthenticated
                        } // Это означает, что нужно пройти авторизацию заново

                        is NotAuthenticated -> {
                            _authState.value = AuthState.NoAuthenticated
                        } // Пользователь понимает, что сначала нужно авторизоваться
                    }
                }
            })

        }
    }

    fun registration() {
        _authState.value = AuthState.Loading

        val vkidAuthCallback = object : VKIDAuthCallback {
            override fun onAuth(accessToken: AccessToken) {
                _authState.value = AuthState.Authenticated(accessToken)
            }

            override fun onFail(fail: VKIDAuthFail) {
                when (fail) {
                    is VKIDAuthFail.Canceled -> {
                        _authState.value = AuthState.NoAuthenticated
                    }

                    else -> _authState.value = AuthState.Error(fail.description)
                }

            }
        }

        viewModelScope.launch {
            vkid.authorize(vkidAuthCallback)
        }
    }
}