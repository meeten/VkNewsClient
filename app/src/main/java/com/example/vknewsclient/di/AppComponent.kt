package com.example.vknewsclient.di

import com.example.vknewsclient.presention.main.MainActivity
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
        CommentsSubcomponentModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun commentsComponentFactory(): CommentsComponent.Factory
}