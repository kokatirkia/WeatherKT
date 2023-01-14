package com.example.weather.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilTest {
    @Test
    fun `format long date&time to string`(){
        val pattern = "EEE, d MMM HH:mm a"
        val dateTimeString = "Sat, 14 Jan 18:23 PM"
        val dateTimeLong = 1673706187L

        val convertedDate = DateUtil.longToString(dateTimeLong,pattern)
        assertEquals(convertedDate,dateTimeString)
    }
}