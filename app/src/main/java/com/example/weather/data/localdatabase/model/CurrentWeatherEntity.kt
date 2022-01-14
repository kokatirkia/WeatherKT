package com.example.weather.data.localdatabase.model

data class CurrentWeatherEntity(
    val weatherDescriptionEntity: List<WeatherDescriptionEntity>,
    val mainEntity: MainEntity,
    val windEntity: WindEntity,
    val sysEntity: SysEntity,
    val dt: Long,
    var name: String
)

data class WeatherDescriptionEntity(
    val main: String,
    var description: String,
    val icon: String
)

data class MainEntity(
    var temp: Double,
    var feels_like: String,
    var pressure: Int,
    var humidity: Int
)

data class WindEntity(var speed: Double)

data class SysEntity(
    var sunrise: Long,
    var sunset: Long
)


