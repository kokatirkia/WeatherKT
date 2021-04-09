package com.example.weather.domain.model

data class ExtendedWeather(
    val list: List<WeatherExtendedData>
)

data class WeatherExtendedData(
    val dt: String,
    val main: MainExtended,
    val dtTxt: String,
    val weather: List<DescriptionExtended>,
    val wind: WindExtended
)

data class WindExtended(val speed: Double)

data class DescriptionExtended(
    val description: String,
    val icon: String
)

data class MainExtended(
    val temp: Double,
    val pressure: String,
    val humidity: String
)