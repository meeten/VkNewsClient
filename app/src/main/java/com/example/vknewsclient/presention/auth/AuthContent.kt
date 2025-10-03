package com.example.vknewsclient.presention.auth

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.models.AuthState
import com.example.vknewsclient.presention.main.MainScreen
import com.vk.id.VKID

@Composable
fun AuthContent() {
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(VKID.instance))
    val authState = viewModel.authState.observeAsState(AuthState.Loading).value

    when (authState) {
        is AuthState.Loading -> LoadingScreen()

        is AuthState.NoAuthenticated -> LoginScreen()

        is AuthState.Authenticated -> MainScreen()

        is AuthState.Error -> {
            Log.e("Authentication Error", authState.failDescription)
        }
    }
}