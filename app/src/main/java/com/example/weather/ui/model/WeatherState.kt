package com.example.weather.ui.model

import com.example.weather.utils.ResponseMessageEnum

data class WeatherState(
    var weatherUi: WeatherUi? = null,
    var responseMessage: ResponseMessageEnum? = null,
    var loading: Boolean = false
)
