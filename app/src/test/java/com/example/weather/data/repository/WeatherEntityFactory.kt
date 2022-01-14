package com.example.weather.data.repository

import com.example.weather.data.localdatabase.model.*
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
                WeatherDescriptionEntity(
                    makeRandomString(),
                    makeRandomString(),
                    makeRandomString()
                )
            ),
            MainEntity(makeRandomDouble(), makeRandomString(), makeRandomInt(), makeRandomInt()),
            WindEntity(makeRandomDouble()),
            SysEntity(makeRandomLong(), makeRandomLong()),
            makeRandomLong(),
            makeRandomString()
        ),
        extendedWeather = ExtendedWeatherEntity(
            listOf(
                WeatherExtendedDataEntity(
                    makeRandomLong(),
                    MainExtendedEntity(makeRandomDouble(), makeRandomInt(), makeRandomInt()),
                    makeRandomString(),
                    listOf(DescriptionExtendedEntity(makeRandomString(), makeRandomString())),
                    WindExtendedEntity(makeRandomDouble())
                )
            )
        )
    )
}