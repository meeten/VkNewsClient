package com.example.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vknewsclient.domain.models.FeedPost

class NavigationState(val navController: NavHostController) {
    fun navigateTo(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToComment(feedPost: FeedPost) {
        navController.navigate(Screen.Comment.createRouteWithArgs(feedPost))
    }
}

@Composable
fun rememberNavigationState(
    navController: NavHostController = rememberNavController(),
): NavigationState {
    return remember { NavigationState(navController) }
}