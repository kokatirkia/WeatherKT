package com.example.weather.ui.model

data class ExtendedWeatherUi(
    val list: List<WeatherExtendedDataUi>
)

data class WeatherExtendedDataUi(
    val dt: String,
    val main: MainExtendedUi,
    val dtTxt: String,
    val weather: List<DescriptionExtendedUi>,
    val wind: WindExtendedUi,
    var isExpanded: Boolean = false
)

data class WindExtendedUi(val speed: String)

data class DescriptionExtendedUi(
    val description: String,
    val icon: String
)

data class MainExtendedUi(
    val temp: String,
    val pressure: String,
    val humidity: String
)