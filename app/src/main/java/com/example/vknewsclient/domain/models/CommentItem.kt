package com.example.vknewsclient.domain.models

import com.example.vknewsclient.R

data class CommentItem(
    val id: Int,
    val authorAvatar: Int = R.drawable.comment_author_avatar,
    val authorName: String = "Author",
    val commentContent: String = "comment text",
    val publicationTime: String = "14:00",
)