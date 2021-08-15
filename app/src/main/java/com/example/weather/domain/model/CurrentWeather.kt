package com.example.weather.domain.model

data class CurrentWeather(
    val weatherDescription: List<WeatherDescription>,
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    val day: String,
    val time: String,
    var name: String,
)

data class WeatherDescription(
    val main: String,
    var description: String,
    val icon: String
)

data class Main(
    var temp: String,
    var feelsLike: String,
    var pressure: Int,
    var humidity: Int
)

data class Wind(var speed: Double)

data class Sys(
    var sunrise: String,
    var sunset: String
)