package com.example.vknewsclient.data.repository

import com.example.vknewsclient.di.ApplicationScope
import com.example.vknewsclient.domain.repository.AuthRepository
import com.example.vknewsclient.domain.AuthStatus
import com.example.vknewsclient.domain.repository.NewsFeedRepository
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.auth.VKIDAuthParams
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@ApplicationScope
class AuthRepositoryImpl @Inject constructor(
    private val newsFeedRepository: NewsFeedRepository
) : AuthRepository {

    private val vkid = VKID.instance

    override fun checkAuth(): AuthStatus {
        val token = vkid.accessToken
        val authStatus = if (token != null && token.expireTime != 0L) {
            AuthStatus.AUTHORIZED
        } else {
            AuthStatus.UNAUTHORIZED
        }
        return authStatus
    }

    override fun observeTokenEvents(): SharedFlow<Unit> {
        return newsFeedRepository.tokenValidEvents
    }

    override suspend fun authorized(): AuthStatus {
        val resultDeferred =
            CompletableDeferred<AuthStatus>()

        val vkidAuthCallback = object : VKIDAuthCallback {
            override fun onAuth(accessToken: AccessToken) {
                resultDeferred.complete(AuthStatus.AUTHORIZED)
            }

            override fun onFail(fail: VKIDAuthFail) {
                resultDeferred.completeExceptionally(
                    Exception(fail.description)
                )
            }
        }

        vkid.authorize(vkidAuthCallback, params = VKIDAuthParams {
            scopes = setOf("friends", "wall")
        })

        val status = resultDeferred.await()
        return status.also { status ->
            if (status == AuthStatus.AUTHORIZED) {
                newsFeedRepository.resetAndLoadData()
            }
        }
    }
}