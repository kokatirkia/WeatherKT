package com.example.weather.data.networking.model

data class WeatherResponse(
    val currentWeatherApi: CurrentWeatherResponse,
    val extendedWeatherApi: ExtendedWeatherResponse
)
