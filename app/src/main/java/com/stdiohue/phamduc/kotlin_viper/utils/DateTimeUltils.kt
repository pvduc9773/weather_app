package com.stdiohue.phamduc.kotlin_viper.utils

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateTimeUltils {
    companion object {
        fun longToDateName(date: Long): String {
            val date = Date(TimeUnit.SECONDS.toMillis(date))
            val dateFormat = SimpleDateFormat("E", Locale.US)
            when (dateFormat.format(date)) {
                "Mon" -> return "Monday"
                "Tue" -> return "Tuesday"
                "Wed" -> return "Wednesday"
                "Thu" -> return "Thursday"
                "Fri" -> return "Friday"
                "Sat" -> return "Saturday"
                "Sun" -> return "Sunday"
            }

            return dateFormat.format(date)
        }

        fun longToDate(date: Long): String {
            val date = Date(TimeUnit.SECONDS.toMillis(date))
            val dateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy")
            return dateFormat.format(date)
        }
    }
}