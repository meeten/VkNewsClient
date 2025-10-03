package com.example.vknewsclient.presention.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen() {
    Scaffold(bottomBar = {
        BottomAppBar {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }) {
        Column(modifier = Modifier.padding(it)) {
            LoginScreen { }
        }
    }
}