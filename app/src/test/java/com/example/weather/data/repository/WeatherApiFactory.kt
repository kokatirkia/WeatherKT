package com.example.weather.data.repository

import com.example.weather.data.networking.model.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object WeatherApiFactory {
    private fun makeRandomString() = UUID.randomUUID().toString()

    private fun makeRandomInt() =
        ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    private fun makeRandomDouble() =
        ThreadLocalRandom.current().nextDouble(0.0, 1000.0 + 1.0)

    private fun makeRandomLong() =
        ThreadLocalRandom.current().nextLong(0L, 1000L + 1L)

    fun makeCurrentWeatherApi() = CurrentWeatherApi(
        listOf(CurrentWeatherDataApi(makeRandomString(), makeRandomString(), makeRandomString())),
        MainApi(makeRandomString(), makeRandomString(), makeRandomInt(), makeRandomInt()),
        WindApi(makeRandomDouble()),
        SysApi(makeRandomLong(), makeRandomLong()),
        makeRandomLong(),
        makeRandomString()
    )

    fun makeExtendedWeatherApi() = ExtendedWeatherApi(
        makeRandomDouble(),
        listOf(
            WeatherDataApi(
                makeRandomLong(),
                MainExtendedApi(makeRandomDouble(), makeRandomString(), makeRandomString()),
                makeRandomString(),
                listOf(DescriptionExtendedApi(makeRandomString(), makeRandomString())),
                WindExtendedApi(makeRandomDouble())
            )
        )
    )
}