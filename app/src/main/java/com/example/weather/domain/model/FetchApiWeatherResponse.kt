package com.example.weather.domain.model

data class FetchWeatherResponse(
    var weather: Weather? = null,
    var responseMessage: String? = null,
    var errorWhileFetching: Boolean = false,
    var noInternetConnection: Boolean = false,
)