package com.example.vknewsclient.presention.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.di.LocalViewModelFactory
import com.example.vknewsclient.presention.auth.AuthContent

@Composable
fun RootScreen() {
    val viewModelFactory = LocalViewModelFactory.current
    val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
    val rootState = viewModel.rootState.collectAsState(RootState.Initial)

    val currentState = rootState.value
    when (currentState) {
        RootState.Initial -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        RootState.Authorized -> {
            MainScreen()
        }

        RootState.Unauthorized -> {
            AuthContent {
                viewModel.onAuthorized()
            }
        }
    }
}