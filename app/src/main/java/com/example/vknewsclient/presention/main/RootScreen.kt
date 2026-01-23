package com.example.vknewsclient.presention.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.state.RootState
import com.example.vknewsclient.presention.auth.AuthContent

@Composable
fun RootScreen() {
    val viewModel: MainViewModel = viewModel()
    val rootState = viewModel.rootState.observeAsState(RootState.Initial).value

    when (rootState) {
        RootState.Initial -> {}

        RootState.Authorized -> {
            MainScreen()
        }

        RootState.Unauthorized -> {
            Log.d("RootScreen", "Unauthorized")
            AuthContent {
                viewModel.onAuthorized()
            }
        }
    }
}