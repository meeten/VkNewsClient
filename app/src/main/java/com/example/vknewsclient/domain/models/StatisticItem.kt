package com.example.vknewsclient.domain.models

import com.example.vknewsclient.R

data class StatisticItem(
    val type: StatisticItemType,
    val count: Int,
)

enum class StatisticItemType(val src: Int) {
    LIKES(src = R.drawable.ic_like),
    COMMENTS(src = R.drawable.ic_comment),
    SHARES(src = R.drawable.ic_share),
    VIEWS(src = R.drawable.ic_views_count)
}