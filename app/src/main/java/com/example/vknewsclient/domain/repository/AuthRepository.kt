package com.example.vknewsclient.domain.repository

import com.example.vknewsclient.domain.AuthStatus
import kotlinx.coroutines.flow.SharedFlow

interface AuthRepository {

    fun checkAuth(): AuthStatus

    fun observeTokenEvents(): SharedFlow<Unit>

    suspend fun authorized(): AuthStatus
}