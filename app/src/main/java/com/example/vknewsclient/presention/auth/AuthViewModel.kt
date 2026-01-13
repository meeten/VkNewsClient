package com.example.vknewsclient.presention.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.domain.state.AuthState
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.auth.VKIDAuthParams
import com.vk.id.refresh.VKIDRefreshTokenCallback
import com.vk.id.refresh.VKIDRefreshTokenFail
import kotlinx.coroutines.launch

class AuthViewModel(val vkid: VKID) : ViewModel() {
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> get() = _authState

    init {
        checkToken()
    }

    fun registration() {
        Log.d("AuthViewModel", "registration")
        _authState.value = AuthState.Loading

        val vkidAuthCallback = object : VKIDAuthCallback {
            override fun onAuth(accessToken: AccessToken) {
                _authState.value = AuthState.Authenticated(accessToken)
            }

            override fun onFail(fail: VKIDAuthFail) {
                _authState.value = AuthState.Error(fail.description)
            }
        }

        viewModelScope.launch {
            vkid.authorize(vkidAuthCallback, params = VKIDAuthParams {
                scopes = setOf("friends", "wall")
            })
        }
    }

    private fun checkToken() {
        Log.d("AuthViewModel", "checkToken")
        vkid.accessToken?.let {
            _authState.value = AuthState.Authenticated(it)
        } ?: refreshToken()
    }

    private fun refreshToken() {
        Log.d("AuthViewModel", "refreshToken")
        viewModelScope.launch {
            VKID.instance.refreshToken(
                callback = object : VKIDRefreshTokenCallback {
                    override fun onSuccess(token: AccessToken) {
                        _authState.value = AuthState.Authenticated(token)
                    }

                    override fun onFail(fail: VKIDRefreshTokenFail) {
                        when (fail) {
                            is VKIDRefreshTokenFail.NotAuthenticated -> {
                                _authState.value =
                                    AuthState.NoAuthenticated
                            }

                            is VKIDRefreshTokenFail.RefreshTokenExpired -> {
                                _authState.value =
                                    AuthState.NoAuthenticated
                            }

                            else -> {
                                _authState.value = AuthState.Error(fail.description)
                            }
                        }
                    }
                }
            )
        }
    }
}