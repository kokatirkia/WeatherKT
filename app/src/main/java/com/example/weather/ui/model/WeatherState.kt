package com.example.weather.ui.model

data class WeatherState(
    var weatherUi: WeatherUi? = null,
    var responseMessage: String? = null,
    var errorWhileFetching: Boolean = false,
    var noInternetConnection: Boolean = false,
)
