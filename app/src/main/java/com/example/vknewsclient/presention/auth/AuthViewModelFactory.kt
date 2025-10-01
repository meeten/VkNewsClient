package com.example.vknewsclient.presention.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vk.id.VKID

class AuthViewModelFactory(private val vkid: VKID) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(vkid) as T
        }

        throw IllegalStateException("unknown View Model: $modelClass")
    }
}