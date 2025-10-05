package com.example.vknewsclient.presention.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vknewsclient.navigation.Screen

sealed class NavigationItem(val screen: Screen, val imageVector: ImageVector, val title: String) {
    object Home :
        NavigationItem(screen = Screen.Home, imageVector = Icons.Outlined.Home, title = "Главная")

    object Favorite : NavigationItem(
        screen = Screen.Favorite, imageVector = Icons.Outlined.FavoriteBorder, title = "Избранное"
    )

    object Profile : NavigationItem(
        screen = Screen.Profile, imageVector = Icons.Outlined.Person, title = "Профиль"
    )
}