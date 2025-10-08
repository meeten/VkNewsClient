package com.example.vknewsclient.navigation

import com.example.vknewsclient.domain.models.FeedPost

sealed class Screen(val route: String) {
    object Comment : Screen(COMMENT_ROUTE) {
        const val COMMENT_ROUTE_WITH_ARGS = "comment"

        fun createRouteWithArgs(feedPost: FeedPost): String {
            return "$COMMENT_ROUTE_WITH_ARGS/${feedPost.id}"
        }
    }

    companion object {
        const val KEY_FEED_POST_ID = "feedPostId"

        const val HOME_ROUTE = "home"
        const val NEWS_ROUTE = "news"
        const val COMMENT_ROUTE = "comment/{$KEY_FEED_POST_ID}"
        const val FAVORITE_ROUTE = "favorite"
        const val PROFILE_ROUTE = "profile"
    }

    object Home : Screen(HOME_ROUTE)
    object News : Screen(NEWS_ROUTE)
    object Favorite : Screen(FAVORITE_ROUTE)
    object Profile : Screen(PROFILE_ROUTE)
}