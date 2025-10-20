package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.data.model.CommentsResponseDto
import com.example.vknewsclient.domain.models.CommentItem
import com.example.vknewsclient.domain.models.Profile
import kotlin.math.absoluteValue

class CommentItemMapper {

    fun mapResponseToComments(response: CommentsResponseDto): List<CommentItem> {
        val result = mutableListOf<CommentItem>()

        val comments = response.commentsContentDto.comments
        val profiles = response.commentsContentDto.profiles

        for (commentItemDto in comments) {
            val profile =
                profiles.find { it.id == commentItemDto.profileId.absoluteValue } ?: continue

            val commentItem = CommentItem(
                id = commentItemDto.id,
                text = commentItemDto.text,
                publicationTime = commentItemDto.dateUrl,
                profile = Profile(
                    id = profile.id,
                    firstName = profile.firstName,
                    lastName = profile.lastName,
                    profilePhoto100Url = profile.profilePhoto100Url
                )
            )

            result.add(commentItem)
        }

        return result
    }
}