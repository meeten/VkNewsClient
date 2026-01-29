package com.example.vknewsclient.domain

import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetTokenValidEventsUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(): SharedFlow<Unit> {
        return repository.observeTokenEvents()
    }
}