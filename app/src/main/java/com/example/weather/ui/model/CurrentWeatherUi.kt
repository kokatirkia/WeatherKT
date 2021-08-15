package com.example.weather.ui.model

data class CurrentWeatherUi(
    val weather: List<WeatherDescriptionUi>,
    val main: MainUi,
    val wind: WindUi,
    val sys: SysUi,
    val day: String,
    val time: String,
    var name: String
)

data class WeatherDescriptionUi(
    val main: String,
    var description: String,
    val icon: String
)

data class MainUi(
    var temp: String,
    var feelsLike: String,
    var pressure: String,
    var humidity: String
)

data class WindUi(var speed: String)

data class SysUi(
    var sunrise: String,
    var sunset: String
)
