package com.example.weather.ui.model

data class ExtendedWeatherUi(
    val list: List<WeatherExtendedDataUi>
)

data class WeatherExtendedDataUi(
    val dt: Long,
    val main: MainExtendedUi,
    val dt_txt: String,
    val weather: List<DescriptionExtendedUi>,
    val wind: WindExtendedUi,
    var expanded: Boolean = false
)

data class WindExtendedUi(val speed: Double)

data class DescriptionExtendedUi(
    val description: String,
    val icon: String
)

data class MainExtendedUi(
    val temp: Double,
    val pressure: String,
    val humidity: String
)