package com.example.weather.data.repository

import com.example.weather.data.networking.model.CurrentWeatherResponse
import com.example.weather.data.networking.model.ExtendedWeatherResponse
import com.example.weather.data.networking.model.WeatherResponse

object WeatherApiFactory {
    const val main = "Clear"
    const val description = "clear"
    const val icon = "01n"
    const val temp = 8.84
    const val feelsLike = "8.84"
    const val pressure = 1018
    const val humidity = 49
    const val speed = 8.23
    const val dt = 1641391386L
    const val dtTxt = "2023-02-11 18:00:00"
    const val sunrise = 1641356867L
    const val sunset = 1641390223L
    const val name: String = "Tbilisi"
    const val message = 0.0

    fun makeWeatherResponse() = WeatherResponse(
        makeCurrentWeatherResponse(),
        makeExtendedWeatherResponse()
    )

    fun makeCurrentWeatherResponse() = CurrentWeatherResponse(
        listOf(CurrentWeatherResponse.Weather(main, description, icon)),
        CurrentWeatherResponse.Main(temp, feelsLike, pressure, humidity),
        CurrentWeatherResponse.Wind(speed),
        CurrentWeatherResponse.Sys(sunrise, sunset),
        dt,
        name
    )

    fun makeExtendedWeatherResponse() = ExtendedWeatherResponse(
        message,
        listOf(
            ExtendedWeatherResponse.WeatherItem(
                dt,
                ExtendedWeatherResponse.Main(temp, pressure, humidity),
                dtTxt,
                listOf(ExtendedWeatherResponse.Weather(description, icon)),
                ExtendedWeatherResponse.Wind(speed)
            )
        )
    )
}