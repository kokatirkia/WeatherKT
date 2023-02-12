package com.example.weather.data.repository

import com.example.weather.data.localdatabase.model.CurrentWeatherEntity
import com.example.weather.data.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.data.localdatabase.model.WeatherEntity

object WeatherEntityFactory {
    private const val main = "Clear"
    private const val description = "clear"
    private const val icon = "01n"
    private const val temp = 8.84
    private const val feelsLike = "8.84"
    private const val pressure = 1018
    private const val humidity = 49
    private const val speed = 8.23
    private const val dt = 1641391386L
    private const val dtTxt = "2023-02-11 18:00:00"
    private const val sunrise = 1641356867L
    private const val sunset = 1641390223L
    private const val name: String = "Tbilisi"

    fun makeWeatherEntity() = WeatherEntity(
        currentWeather = CurrentWeatherEntity(
            listOf(CurrentWeatherEntity.Weather(main, description, icon)),
            CurrentWeatherEntity.Main(temp, feelsLike, pressure, humidity),
            CurrentWeatherEntity.Wind(speed),
            CurrentWeatherEntity.Sys(sunrise, sunset),
            dt,
            name
        ),
        extendedWeather = ExtendedWeatherEntity(
            listOf(
                ExtendedWeatherEntity.WeatherItem(
                    dt,
                    ExtendedWeatherEntity.Main(temp, pressure, humidity),
                    dtTxt,
                    listOf(ExtendedWeatherEntity.Weather(description, icon)),
                    ExtendedWeatherEntity.Wind(speed)
                )
            )
        )
    )
}