package com.example.weather.data.networking.model

data class CurrentWeatherApi(
    val weather: List<WeatherApi>,
    val main: MainApi,
    val wind: WindApi,
    val sys: SysApi,
    var name: String
)

data class WeatherApi(
    val main: String,
    var description: String,
    val icon: String
)

data class MainApi(
    var temp: String,
    var feels_like: String,
    var pressure: Int,
    var humidity: Int
)

data class WindApi(var speed: Double)

data class SysApi(
    var sunrise: Long,
    var sunset: Long
)