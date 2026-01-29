package com.example.vknewsclient.di

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.vknewsclient.presention.main.ViewModelFactory

val LocalAppComponent =
    staticCompositionLocalOf<AppComponent> {
        error(
            "ViewModelFactory is not provided. " +
                    "Make sure it is supplied via CompositionLocalProvider"
        )
    }
val LocalViewModelFactory =
    staticCompositionLocalOf<ViewModelFactory> {
        error(
            "ViewModelFactory not provided! " +
                    "Check if it's provided in CompositionLocalProvider"
        )
    }