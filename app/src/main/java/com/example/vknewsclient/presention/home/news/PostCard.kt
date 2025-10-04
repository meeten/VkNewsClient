package com.example.vknewsclient.presention.home.news

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknewsclient.R
import com.example.vknewsclient.ui.theme.VkNewsClientTheme
import kotlin.random.Random

@Composable
fun PostCard(modifier: Modifier) {
    Card(
        modifier = Modifier.padding(10.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary)
    ) {
        Column {
            PostHeader()

            Post()

            Statistics()
        }
    }
}

@Composable
private fun PostHeader() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.post_comunity_thumbnail),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "/dev/null",
                style = TextStyle(
                    fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "14:00",
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
private fun Post() {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(text = "content content content content content")

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(R.drawable.post_content_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun Statistics() {
    Row(modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.weight(1f)) {
            ImageTextLayout(R.drawable.ic_like, Random.nextInt(10, 100))

            Spacer(modifier = Modifier.width(14.dp))

            ImageTextLayout(R.drawable.ic_comment, Random.nextInt(10, 100))

            Spacer(modifier = Modifier.width(14.dp))

            ImageTextLayout(R.drawable.ic_share, Random.nextInt(10, 100))
        }

        ImageTextLayout(R.drawable.ic_views_count, Random.nextInt(10, 100))
    }
}

@Composable
private fun ImageTextLayout(src: Int, count: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
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

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun PostCardLight() {
    VkNewsClientTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            PostCard(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun PostCardDark() {
    VkNewsClientTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            PostCard(
                modifier = Modifier
            )
        }
    }
}