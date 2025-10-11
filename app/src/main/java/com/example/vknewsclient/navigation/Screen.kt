package com.example.vknewsclient.navigation

import android.net.Uri
import com.example.vknewsclient.domain.models.FeedPost
import com.google.gson.Gson

sealed class Screen(val route: String) {
    object Comment : Screen(COMMENT_ROUTE) {
        const val COMMENT_ROUTE_WITH_ARGS = "comment"

        fun createRouteWithArgs(feedPost: FeedPost): String {
            val feedPostGson = Gson().toJson(feedPost)
            return "$COMMENT_ROUTE_WITH_ARGS/${feedPostGson.encode()}"
        }
    }

    object Home : Screen(HOME_ROUTE)
    object News : Screen(NEWS_ROUTE)
    object Favorite : Screen(FAVORITE_ROUTE)
    object Profile : Screen(PROFILE_ROUTE)

    companion object {
        const val KEY_FEED_POST = "feedPost"

        const val HOME_ROUTE = "home"
        const val NEWS_ROUTE = "news"
        const val COMMENT_ROUTE = "comment/{$KEY_FEED_POST}"
        const val FAVORITE_ROUTE = "favorite"
        const val PROFILE_ROUTE = "profile"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}