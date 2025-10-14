package com.example.vknewsclient.presention.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.vknewsclient.navigation.NavigationState
import com.example.vknewsclient.navigation.rememberNavigationState
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun MainNavigationBottomBar(navigationState: NavigationState) {

    val navBackStackEntry = navigationState.navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    val navigationItems = listOf<NavigationItem>(
        NavigationItem.Home, NavigationItem.Favorite,
        NavigationItem.Profile
    )

    Column() {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.secondary
        )

        BottomAppBar {
            navigationItems.forEach { item ->
                val selected = currentDestination?.hierarchy?.any {
                    it.route == item.screen.route
                } == true
                NavigationBarItem(
                    selected = selected,
                    onClick = { navigationState.navigateTo(item.screen.route) },
                    icon = { Icon(imageVector = item.imageVector, contentDescription = null) },
                    label = { Text(text = item.title) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    )
                )
            }
        }
    }
}