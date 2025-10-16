package com.example.vknewsclient.presention.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.state.NewsFeedScreenState
import com.example.vknewsclient.presention.home.news.PostCard
import com.example.vknewsclient.ui.theme.DarkBlue

@Composable
fun HomeScreen(modifier: Modifier, onCommentClickListener: (FeedPost) -> Unit) {
    val viewModel: HomeViewModel = viewModel()
    val newsFeedScreenState =
        viewModel.newsFeedScreenState.observeAsState(NewsFeedScreenState.Initial).value

    val currentState = newsFeedScreenState
    when (currentState) {
        is NewsFeedScreenState.Posts -> {
            NewsFeedContent(
                currentState.feedPosts,
                currentState.isLoadingNextPosts,
                viewModel,
                onCommentClickListener,
            )
        }

        is NewsFeedScreenState.Initial -> {

        }
    }
}

@Composable
private fun NewsFeedContent(
    feedPosts: List<FeedPost>,
    isLoadingNextPosts: Boolean,
    viewModel: HomeViewModel,
    onCommentClickListener: (FeedPost) -> Unit,
) {

    LazyColumn(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 8.dp,
            bottom = 100.dp
        )
    ) {
        items(items = feedPosts, key = { it.id }) { feedPost ->
            PostCard(
                feedPost = feedPost,
                onLikeClickListener = { viewModel.changeLikeStatus(feedPost) },
                onCommentClickListener = { onCommentClickListener(feedPost) },
                onShareClickListener = { viewModel.incrementStatistics(feedPost, it) },
                onViewClickListener = { viewModel.incrementStatistics(feedPost, it) }
            )
        }

        item {
            if (isLoadingNextPosts) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = DarkBlue)
                }
            } else {
                SideEffect {
                    viewModel.loadNextPosts()
                }
            }
        }
    }
}