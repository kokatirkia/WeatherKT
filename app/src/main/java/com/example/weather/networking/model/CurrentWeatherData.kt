package com.example.weather.networking.model

data class CurrentWeatherData(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    var name: String
)

data class Main(
    var temp: String,
    var feels_like: String,
    var pressure: Int,
    var humidity: Int
)

data class Wind(var speed: Double)

data class Sys(
    var sunrise: Long,
    var sunset: Long
)

data class Weather(
    val main: String,
    var description: String,
    val icon: String
)


