package com.example.weather.data.repository

import com.example.weather.data.localdatabase.model.CurrentWeatherEntity
import com.example.weather.data.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.data.localdatabase.model.WeatherEntity
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object WeatherEntityFactory {

    private fun makeRandomString() = UUID.randomUUID().toString()

    private fun makeRandomInt() =
        ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    private fun makeRandomDouble() =
        ThreadLocalRandom.current().nextDouble(0.0, 1000.0 + 1.0)

    private fun makeRandomLong() =
        ThreadLocalRandom.current().nextLong(0L, 1000L + 1L)

    fun makeWeatherEntity() = WeatherEntity(
        id = 1,
        currentWeather = CurrentWeatherEntity(
            listOf(
                CurrentWeatherEntity.Weather(
                    makeRandomString(),
                    makeRandomString(),
                    makeRandomString()
                )
            ),
            CurrentWeatherEntity.Main(
                makeRandomDouble(),
                makeRandomString(),
                makeRandomInt(),
                makeRandomInt()
            ),
            CurrentWeatherEntity.Wind(makeRandomDouble()),
            CurrentWeatherEntity.Sys(makeRandomLong(), makeRandomLong()),
            makeRandomLong(),
            makeRandomString()
        ),
        extendedWeather = ExtendedWeatherEntity(
            listOf(
                ExtendedWeatherEntity.WeatherItem(
                    makeRandomLong(),
                    ExtendedWeatherEntity.Main(
                        makeRandomDouble(),
                        makeRandomInt(),
                        makeRandomInt()
                    ),
                    makeRandomString(),
                    listOf(ExtendedWeatherEntity.Weather(makeRandomString(), makeRandomString())),
                    ExtendedWeatherEntity.Wind(makeRandomDouble())
                )
            )
        )
    )
}