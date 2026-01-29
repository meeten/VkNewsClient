package com.example.vknewsclient.domain

import kotlinx.coroutines.flow.SharedFlow

interface AuthRepository {

    fun checkAuth(): AuthStatus

    fun observeTokenEvents(): SharedFlow<Unit>

    suspend fun authorized(): AuthStatus
}