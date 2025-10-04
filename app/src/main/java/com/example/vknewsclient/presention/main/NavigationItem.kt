package com.example.vknewsclient.presention.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val imageVector: ImageVector, val title: String) {
    object Home : NavigationItem(imageVector = Icons.Outlined.Home, title = "Главная")

    object Favorite : NavigationItem(imageVector = Icons.Outlined.Favorite, title = "Избранное")

    object Profile : NavigationItem(imageVector = Icons.Outlined.Person, title = "Профиль")
}