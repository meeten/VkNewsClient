package com.example.vknewsclient.presention.home.news

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.domain.models.StatisticItem
import com.example.vknewsclient.domain.models.StatisticItemType

@Composable
fun PostCard(
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onViewClickListener: (StatisticItem) -> Unit,
) {
    Card(
        modifier = Modifier.padding(10.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary)
    ) {
        Column {
            PostHeader(feedPost)

            Post(feedPost)

            Statistics(
                feedPost.statistics,
                onLikeClickListener,
                onCommentClickListener,
                onShareClickListener,
                onViewClickListener
            )
        }
    }
}

@Composable
private fun PostHeader(feedPost: FeedPost) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(feedPost.publicImage),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = feedPost.publicName,
                style = TextStyle(
                    fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = feedPost.publicationTime,
                style = TextStyle(
                    fontSize = 16.sp, color = MaterialTheme.colorScheme.onSecondary
                )
            )
        }

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun Post(feedPost: FeedPost) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(text = feedPost.postContent)

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(feedPost.postContentImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onViewClickListener: (StatisticItem) -> Unit,
) {
    val likes = statistics.findStatisticItemByType(StatisticItemType.LIKES)
    val comments = statistics.findStatisticItemByType(StatisticItemType.COMMENTS)
    val shares = statistics.findStatisticItemByType(StatisticItemType.SHARES)
    val views = statistics.findStatisticItemByType(StatisticItemType.VIEWS)

    Row(modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.weight(1f)) {
            ImageTextLayout(likes.type.src, likes.count, {
                onLikeClickListener(likes)
            })

            Spacer(modifier = Modifier.width(14.dp))

            ImageTextLayout(comments.type.src, comments.count, {
                onCommentClickListener(comments)
            })

            Spacer(modifier = Modifier.width(14.dp))

            ImageTextLayout(shares.type.src, shares.count, {
                onShareClickListener(shares)
            })
        }

        ImageTextLayout(views.type.src, views.count, {
            onViewClickListener(views)
        })
    }
}

@Composable
private fun ImageTextLayout(src: Int, count: Int, onItemClickListener: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
        onItemClickListener()
    }) {
        Image(
            painter = painterResource(src),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(7.dp))

        Text(
            text = "$count",
            style = TextStyle(
                fontSize = 16.sp, color = MaterialTheme.colorScheme.onSecondary
            )
        )
    }
}

private fun List<StatisticItem>.findStatisticItemByType(type: StatisticItemType): StatisticItem {
    return this.firstOrNull { it.type == type }
        ?: throw IllegalStateException("Undefined type: $type")
}