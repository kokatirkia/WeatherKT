package com.example.weather.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun longToString(time: Long, pattern: String): String = SimpleDateFormat(
        pattern,
        Locale.ENGLISH
    ).format(Date(time * 1000))
}
