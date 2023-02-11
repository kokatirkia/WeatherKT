package com.example.weather.data.localdatabase.model

data class CurrentWeatherEntity(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    val dt: Long,
    var name: String
) {
    data class Weather(
        val main: String,
        var description: String,
        val icon: String
    )

    data class Main(
        var temp: Double,
        var feels_like: String,
        var pressure: Int,
        var humidity: Int
    )

    data class Wind(var speed: Double)

    data class Sys(
        var sunrise: Long,
        var sunset: Long
    )
}




