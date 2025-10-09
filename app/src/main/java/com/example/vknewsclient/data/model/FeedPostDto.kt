package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedPostDto(
    @SerialName("id") val id: String,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @SerialName("likes") val likesDto: LikesDto,
    @SerialName("comments") val commentsDto: CommentsDto,
    @SerialName("reports") val reportsDto: ReportsDto,
    @SerialName("views") val viewsDto: ViewsDto,
    @SerialName("attachments") val attachmentsDto: List<AttachmentsDto>,
)
