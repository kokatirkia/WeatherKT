package com.example.weather.data.repository

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ExtendedWeather
import com.example.weather.domain.model.Weather
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
            listOf(
                CurrentWeather.Weather(
                    makeRandomString(),
                    makeRandomString(),
                    makeRandomString()
                )
            ),
            CurrentWeather.Main(
                makeRandomDouble(),
                makeRandomString(),
                makeRandomInt(),
                makeRandomInt()
            ),
            CurrentWeather.Wind(makeRandomDouble()),
            CurrentWeather.Sys(makeRandomLong(), makeRandomLong()),
            makeRandomLong(),
            makeRandomString()
        ),
        extendedWeather = ExtendedWeather(
            listOf(
                ExtendedWeather.WeatherItem(
                    makeRandomLong(),
                    ExtendedWeather.Main(makeRandomDouble(), makeRandomInt(), makeRandomInt()),
                    makeRandomString(),
                    listOf(ExtendedWeather.Weather(makeRandomString(), makeRandomString())),
                    ExtendedWeather.Wind(makeRandomDouble())
                )
            )
        )
    )
}