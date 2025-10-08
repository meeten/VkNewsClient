package com.example.vknewsclient.presention.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.rememberNavigationState
import com.example.vknewsclient.presention.comment.CommentScreen
import com.example.vknewsclient.presention.home.HomeScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(bottomBar = {
        MainNavigationBottomBar(navigationState)
    }) {
        AppNavGraph(
            navController = navigationState.navController,
            homeContentScreen = {
                HomeScreen(modifier = Modifier.padding(it)) { feedPost ->
                    navigationState.navigateToComment(feedPost)
                }
            },
            commentContentScreen = { CommentScreen(it) },
            favoriteContentScreen = { TestNavigationState("favorite") },
            profileContentScreen = { TestNavigationState("profile") }
        )
    }
}

@Composable
private fun TestNavigationState(title: String) {
    val countState = remember() { mutableIntStateOf(0) }
    Text(text = "$title: click ${countState.intValue}", modifier = Modifier.clickable {
        countState.intValue += 1
    })
}