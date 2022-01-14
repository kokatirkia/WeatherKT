package com.example.weather.data.repository

import com.example.weather.domain.model.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object WeatherFactory {
    private fun makeRandomString() = UUID.randomUUID().toString()

    private fun makeRandomInt() =
        ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    private fun makeRandomDouble() =
        ThreadLocalRandom.current().nextDouble(0.0, 1000.0 + 1.0)

    private fun makeRandomLong() =
        ThreadLocalRandom.current().nextLong(0L, 1000L + 1L)

    fun makeWeather() = Weather(
        currentWeather = CurrentWeather(
            listOf(WeatherDescription(makeRandomString(), makeRandomString(), makeRandomString())),
            Main(makeRandomDouble(), makeRandomString(), makeRandomInt(), makeRandomInt()),
            Wind(makeRandomDouble()),
            Sys(makeRandomLong(), makeRandomLong()),
            makeRandomLong(),
            makeRandomString()
        ),
        extendedWeather = ExtendedWeather(
            listOf(
                WeatherExtendedData(
                    makeRandomLong(),
                    MainExtended(makeRandomDouble(), makeRandomInt(), makeRandomInt()),
                    makeRandomString(),
                    listOf(DescriptionExtended(makeRandomString(), makeRandomString())),
                    WindExtended(makeRandomDouble())
                )
            )
        )
    )
}