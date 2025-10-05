package com.example.vknewsclient.presention.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.presention.home.news.PostCard

@Composable
fun HomeScreen(modifier: Modifier) {
    val viewModel: HomeViewModel = viewModel()
    val feedPosts = viewModel.feedPosts.observeAsState(emptyList()).value

    LazyColumn(modifier = modifier) {
        items(items = feedPosts, key = { it.id }) { feedPost ->
            val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
                confirmValueChange = {
                    if (it == SwipeToDismissBoxValue.EndToStart) {
                        viewModel.deleteFeedPost(feedPost)
                        true
                    }

                    false
                })

            SwipeToDismissBox(
                state = swipeToDismissBoxState,
                backgroundContent = {},
                modifier = Modifier.animateItem(),
                enableDismissFromStartToEnd = false,
            ) {
                PostCard()
            }
        }
    }
}