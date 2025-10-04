package com.example.vknewsclient.presention.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun MainNavigationBottomBar() {
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
            navigationItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == 0,
                    onClick = {},
                    icon = { Icon(imageVector = item.imageVector, contentDescription = null) },
                    label = { Text(text = item.title) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MainNavigationBottomBarPreviewLight() {
    VkNewsClientTheme(darkTheme = false) {
        MainNavigationBottomBar()
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MainNavigationBottomBarPreviewDark() {
    VkNewsClientTheme(darkTheme = true) {
        MainNavigationBottomBar()
    }
}