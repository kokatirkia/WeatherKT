package com.example.weather.data.repository

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ExtendedWeather
import com.example.weather.domain.model.Weather

object WeatherFactory {
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

    fun makeWeather() = Weather(
        currentWeather = CurrentWeather(
            listOf(CurrentWeather.Weather(main, description, icon)),
            CurrentWeather.Main(temp, feelsLike, pressure, humidity),
            CurrentWeather.Wind(speed),
            CurrentWeather.Sys(sunrise, sunset),
            dt,
            name
        ),
        extendedWeather = ExtendedWeather(
            listOf(
                ExtendedWeather.WeatherItem(
                    dt,
                    ExtendedWeather.Main(temp, pressure, humidity),
                    dtTxt,
                    listOf(ExtendedWeather.Weather(description, icon)),
                    ExtendedWeather.Wind(speed)
                )
            )
        )
    )
}