package com.example.vknewsclient.domain.usecase

import com.example.vknewsclient.domain.AuthStatus
import com.example.vknewsclient.domain.repository.AuthRepository
import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(): AuthStatus {
        return repository.checkAuth()
    }
}