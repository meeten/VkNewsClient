package com.example.vknewsclient.domain

import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(): AuthStatus {
        return repository.checkAuth()
    }
}