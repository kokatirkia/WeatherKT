package com.example.weather.utils

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtil @Inject constructor() {
    fun longToString(time: Long, pattern: String): String = SimpleDateFormat(
        pattern,
        Locale.ENGLISH
    ).format(Date(time * 1000))
}
