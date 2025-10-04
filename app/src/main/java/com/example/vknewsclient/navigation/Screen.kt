package com.example.vknewsclient.navigation

sealed class Screen(val route: String) {
    companion object {
        const val HOME_ROUTE = "home"
        const val FAVORITE_ROUTE = "favorite"
        const val PROFILE_ROUTE = "profile"
    }

    object Home : Screen(HOME_ROUTE)
    object Favorite : Screen(FAVORITE_ROUTE)
    object Profile : Screen(PROFILE_ROUTE)
}