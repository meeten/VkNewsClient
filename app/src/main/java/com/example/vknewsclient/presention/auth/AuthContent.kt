package com.example.vknewsclient.presention.auth

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.state.AuthState
import com.example.vknewsclient.presention.main.MainScreen
import com.vk.id.VKID

@Composable
fun AuthContent(
    onAuthorized: () -> Unit,
) {
    val viewModel: AuthViewModel = viewModel()
    val authState = viewModel.authState.observeAsState(AuthState.NoAuthenticated).value

    when (authState) {
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
            Log.e("Authentication Error", authState.failDescription)
        }
    }
}