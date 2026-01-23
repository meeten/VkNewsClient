package com.example.vknewsclient.presention.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.repository.NewsFeedRepository
import com.example.vknewsclient.domain.state.AuthState
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.auth.VKIDAuthParams
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {

    private val vkid = VKID.instance
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> get() = _authState

    fun registration() {
        Log.d("AuthViewModel", "registration")
        _authState.value = AuthState.Loading

        val vkidAuthCallback = object : VKIDAuthCallback {
            override fun onAuth(accessToken: AccessToken) {
                _authState.value = AuthState.Authenticated

                viewModelScope.launch {
                    NewsFeedRepository.resetAndLoadData()
                }
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
}