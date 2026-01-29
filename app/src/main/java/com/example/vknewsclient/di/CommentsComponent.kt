package com.example.vknewsclient.di

import com.example.vknewsclient.domain.models.FeedPost
import com.example.vknewsclient.presention.comment.CommentViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface CommentsComponent {

    fun commentViewModelFactory(): CommentViewModelFactory

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance feedPost: FeedPost
        ): CommentsComponent
    }
}