package com.example.vknewsclient.domain.usecase

import com.example.vknewsclient.domain.repository.AuthRepository
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetTokenValidEventsUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(): SharedFlow<Unit> {
        return repository.observeTokenEvents()
    }
}