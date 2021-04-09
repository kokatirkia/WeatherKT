package com.example.weather.ui.model

data class CurrentWeatherUi(
    val weather: List<WeatherDescriptionUi>,
    val main: MainUi,
    val wind: WindUi,
    val sys: SysUi,
    var name: String
)

data class WeatherDescriptionUi(
    val main: String,
    var description: String,
    val icon: String
)

data class MainUi(
    var temp: String,
    var feels_like: String,
    var pressure: Int,
    var humidity: Int
)

data class WindUi(var speed: Double)

data class SysUi(
    var sunrise: Long,
    var sunset: Long
)
