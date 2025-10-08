package com.example.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vknewsclient.domain.models.FeedPost


@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeContentScreen: @Composable () -> Unit,
    commentContentScreen: @Composable (FeedPost) -> Unit,
    favoriteContentScreen: @Composable () -> Unit,
    profileContentScreen: @Composable () -> Unit,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        postNavGraph(homeContentScreen, commentContentScreen)

        composable(Screen.Favorite.route) {
            favoriteContentScreen()
        }

        composable(Screen.Profile.route) {
            profileContentScreen()
        }
    }
}