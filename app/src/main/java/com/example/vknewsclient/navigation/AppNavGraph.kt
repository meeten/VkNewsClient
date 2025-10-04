package com.example.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeContentScreen: @Composable () -> Unit,
    favoriteContentScreen: @Composable () -> Unit,
    profileContentScreen: @Composable () -> Unit,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            homeContentScreen()
        }

        composable(Screen.Favorite.route) {
            favoriteContentScreen()
        }

        composable(Screen.Profile.route) {
            profileContentScreen()
        }
    }
}