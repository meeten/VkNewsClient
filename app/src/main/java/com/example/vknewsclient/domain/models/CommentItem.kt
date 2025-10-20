package com.example.vknewsclient.domain.models

data class CommentItem(
    val id: Long,
    val text: String,
    val publicationTime: String,
    val profile: Profile,
)