package com.example.vknewsclient.presention.main

sealed class RootState {

    object Initial : RootState()
    object Authorized : RootState()
    object Unauthorized : RootState()
}