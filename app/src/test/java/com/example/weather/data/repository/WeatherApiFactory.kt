package com.example.weather.data.repository

import com.example.weather.data.networking.model.CurrentWeatherResponse
import com.example.weather.data.networking.model.ExtendedWeatherResponse
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

    fun makeCurrentWeatherResponse() = CurrentWeatherResponse(
        listOf(
            CurrentWeatherResponse.Weather(
                makeRandomString(),
                makeRandomString(),
                makeRandomString()
            )
        ),
        CurrentWeatherResponse.Main(
            makeRandomDouble(),
            makeRandomString(),
            makeRandomInt(),
            makeRandomInt()
        ),
        CurrentWeatherResponse.Wind(makeRandomDouble()),
        CurrentWeatherResponse.Sys(makeRandomLong(), makeRandomLong()),
        makeRandomLong(),
        makeRandomString()
    )

    fun makeExtendedWeatherApi() = ExtendedWeatherResponse(
        makeRandomDouble(),
        listOf(
            ExtendedWeatherResponse.WeatherItem(
                makeRandomLong(),
                ExtendedWeatherResponse.Main(makeRandomDouble(), makeRandomInt(), makeRandomInt()),
                makeRandomString(),
                listOf(ExtendedWeatherResponse.Weather(makeRandomString(), makeRandomString())),
                ExtendedWeatherResponse.Wind(makeRandomDouble())
            )
        )
    )
}