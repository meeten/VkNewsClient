package com.example.vknewsclient.presention.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.example.vknewsclient.di.LocalAppComponent
import com.example.vknewsclient.di.LocalViewModelFactory
import com.example.vknewsclient.presention.app.VkNewsClientApp
import com.example.vknewsclient.presention.auth.AuthContent
import com.example.vknewsclient.presention.comment.CommentViewModelFactory
import com.example.vknewsclient.ui.theme.VkNewsClientTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as VkNewsClientApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalAppComponent provides component,
                LocalViewModelFactory provides viewModelFactory
            ) {
                VkNewsClientTheme {
                    RootScreen()
                }
            }
        }
    }
}