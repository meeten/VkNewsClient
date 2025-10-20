package com.example.vknewsclient.domain.models

data class Profile(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val profilePhoto100Url: String,
) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}
