package com.example.vknewsclient.domain.models

import com.example.vknewsclient.R

data class StatisticItem(
    val type: StatisticItemType,
    val src: Int,
    val count: Int,
)

enum class StatisticItemType() {
    LIKES(),
    COMMENTS(),
    SHARES(),
    VIEWS()
}