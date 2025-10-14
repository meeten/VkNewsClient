package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class FeedPostDto(
    @SerializedName("id") val id: Int,
    @SerializedName("owner_id") val ownerId: Int,
    @SerializedName("from_id") val fromId: Int,
    @SerializedName("date") val date: Int,
    @SerializedName("text") val text: String,
    @SerializedName("likes") val likesDto: LikesDto,
    @SerializedName("comments") val commentsDto: CommentsDto,
    @SerializedName("reposts") val repostsDto: RepostsDto,
    @SerializedName("views") val viewsDto: ViewsDto,
    @SerializedName("attachments") val attachmentsDto: List<AttachmentsDto>?,
    @SerializedName("is_favorite") val isFavorite: Boolean,
)
