package com.example.vknewsclient.domain.models

data class StatisticItem(
    val type: StatisticItemType,
    val count: Int,
)

enum class StatisticItemType() {
    LIKES(),
    COMMENTS(),
    SHARES(),
    VIEWS()
}