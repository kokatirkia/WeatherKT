package com.example.weather.domain.model

import com.example.weather.utils.ResponseMessageEnum

data class FetchWeatherResponse(
    var weather: Weather? = null,
    var responseMessage: ResponseMessageEnum? = null
)