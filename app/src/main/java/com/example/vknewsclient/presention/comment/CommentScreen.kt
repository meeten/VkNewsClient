package com.example.vknewsclient.presention.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vknewsclient.di.LocalAppComponent
import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.FeedPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(feedPost: FeedPost, onArrowBackClickListener: () -> Unit) {
    val appComponent = LocalAppComponent.current
    val commentsComponent = remember(feedPost) {
        appComponent.commentsComponentFactory().create(feedPost)
    }

    val viewModel: CommentViewModel =
        viewModel(factory = commentsComponent.commentViewModelFactory())

    val commentScreenState = viewModel.commentState
        .collectAsState(CommentScreenState.Initial)

    val currentState = commentScreenState.value
    if (currentState is CommentScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Комментарии") },
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
                items(items = currentState.comments, key = { it.id }) { commentItem ->
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
            .padding(15.dp)
            .fillMaxWidth(),
    ) {

        AsyncImage(
            model = commentItem.profile.profilePhoto100Url,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = commentItem.profile.getFullName(),
                style = TextStyle(fontSize = 13.sp, color = MaterialTheme.colorScheme.onPrimary)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = commentItem.text,
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = commentItem.publicationTime,
                style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSecondary)
            )
        }
    }
}