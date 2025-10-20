package com.example.vknewsclient.domain

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

class TimeConverter {

    private val specialTimeInTimestamp = getSpecialDateInTimestamp()

    fun convertTimestampInDate(timestamp: Int): String {
        val timeDifference = specialTimeInTimestamp - timestamp

        val date = Date(timestamp * 1000L)
        return when {
            timeDifference < 60 -> {
                "только что"
            }

            timeDifference < 86400 -> {
                val format = SimpleDateFormat("HH:mm")
                "сегодня в ${format.format(date)}"
            }

            timeDifference < 2 * 86400 -> {
                val format = SimpleDateFormat("HH:mm")
                "вчера в ${format.format(date)}"
            }

            else -> {
                val format = SimpleDateFormat("d MMM HH:mm")
                return format.format(Date(timestamp * 1000L)).replace(".", " в")
            }
        }
    }

    private fun getSpecialDateInTimestamp(): Long {
        val specialDate = LocalDateTime.now().plusDays(1)
        val year = specialDate.year
        val month = specialDate.monthValue
        val day = specialDate.dayOfMonth
        val specialPatternDate = "$year-$month-$day 00:00:00"
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specialPatternDate).time / 1000L
    }
}