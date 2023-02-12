package com.example.weather.data.networking.model

data class WeatherResponse(
    val currentWeatherResponse: CurrentWeatherResponse,
    val extendedWeatherResponse: ExtendedWeatherResponse
)
