package com.example.vknewsclient.domain

import javax.inject.Inject

class AuthorizedUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): AuthStatus {
        return repository.authorized()
    }
}