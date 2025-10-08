package com.example.vknewsclient.presention.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.state.CommentScreenState
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(feedPost: FeedPost, onArrowBackClickListener: () -> Unit) {
    val viewModel: CommentViewModel = viewModel(factory = CommentViewModelFactory(feedPost))
    val commentScreenState = viewModel.commentScreenState.observeAsState().value

    if (commentScreenState is CommentScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Comments for FeedPost id: ${commentScreenState.feedPost.id}") },
                    navigationIcon = {
                        IconButton(onClick = {
                            onArrowBackClickListener()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(items = commentScreenState.comments, key = { it.id }) { commentItem ->
                    CommentItemContent(commentItem)
                }
            }
        }
    }
}

@Composable
fun CommentItemContent(commentItem: CommentItem) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(commentItem.authorAvatar),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = "${commentItem.authorName} CommentId: ${commentItem.id}",
                style = TextStyle(fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimary)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = commentItem.commentContent,
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary)
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = commentItem.publicationTime,
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CommentScreenPreviewLight() {
    VkNewsClientTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            CommentScreen(FeedPost(0), {})
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CommentScreenPreviewDark() {
    VkNewsClientTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            CommentScreen(FeedPost(0), {})
        }
    }
}