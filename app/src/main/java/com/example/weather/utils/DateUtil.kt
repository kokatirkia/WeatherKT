package com.example.weather.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun longToString(timeInSeconds: Long, pattern: String): String = SimpleDateFormat(
        pattern,
        Locale.ENGLISH
    ).format(Date(timeInSeconds * 1000))
}
