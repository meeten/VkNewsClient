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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun CommentScreen() {

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

        Column() {
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
            CommentItemContent(CommentItem(id = 0))
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CommentScreenPreviewDark() {
    VkNewsClientTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            CommentItemContent(CommentItem(id = 0))
        }
    }
}