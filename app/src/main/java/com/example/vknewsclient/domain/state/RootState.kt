package com.example.vknewsclient.domain.state

sealed class RootState {

    object Initial : RootState()
    object Authorized : RootState()
    object Unauthorized : RootState()
}
