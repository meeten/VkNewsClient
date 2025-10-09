package com.example.vknewsclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedPostDto(
    @SerialName("id") val id: String,
    @SerialName("from_id") val fromId: Long,
    @SerialName("date") val date: String,
    @SerialName("text") val text: String,
    @SerialName("likes") val likesDto: LikesDto,
    @SerialName("comments") val commentsDto: CommentsDto,
    @SerialName("reports") val reportsDto: ReportsDto,
    @SerialName("views") val viewsDto: ViewsDto,
    @SerialName("attachments") val attachmentsDto: List<AttachmentsDto>?,
)
