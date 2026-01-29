package com.example.vknewsclient.presention.auth

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.di.LocalViewModelFactory
import com.example.vknewsclient.presention.auth.AuthState
import com.example.vknewsclient.presention.main.MainScreen
import com.vk.id.VKID

@Composable
fun AuthContent(
    onAuthorized: () -> Unit,
) {
    val viewModelFactory = LocalViewModelFactory.current
    val viewModel: AuthViewModel = viewModel(factory = viewModelFactory)
    val authState =
        viewModel.authState.collectAsState(AuthState.NoAuthenticated)

    val currentState = authState.value
    when (currentState) {
        is AuthState.Loading -> LoadingScreen()

        is AuthState.NoAuthenticated -> {
            LoginScreen({
                viewModel.registration()
            })
        }

        is AuthState.Authenticated -> {
            onAuthorized()
        }

        is AuthState.Error -> {
            Log.e("Authentication Error", currentState.failDescription)
        }
    }
}