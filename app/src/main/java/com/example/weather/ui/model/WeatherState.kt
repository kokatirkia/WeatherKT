package com.example.weather.ui.model

data class WeatherState(
    var weatherUi: WeatherUi? = null,
    var errorMessage: String? = null,
    var loading: Boolean = true
)
