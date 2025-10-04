package com.example.vknewsclient.presention.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vknewsclient.presention.news.PostCard

@Composable
fun MainScreen() {
    Scaffold(bottomBar = {
        MainNavigationBottomBar()
    }) {
        PostCard(modifier = Modifier.padding(it))
    }
}