package com.example.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.vknewsclient.domain.models.FeedPost
import com.google.gson.Gson

fun NavGraphBuilder.postNavGraph(
    feedPostScreenContent: @Composable () -> Unit,
    commentScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(
        startDestination = Screen.News.route,
        route = Screen.Home.route
    ) {
        composable(Screen.News.route) { feedPostScreenContent() }
        composable(
            Screen.Comment.route, arguments = listOf(navArgument(Screen.KEY_FEED_POST) {
                type = NavType.StringType
            })
        ) {
            val feedPost = Gson().fromJson<FeedPost>(
                it.arguments?.getString(Screen.KEY_FEED_POST),
                FeedPost::class.java
            )

            commentScreenContent(feedPost)
        }
    }
}