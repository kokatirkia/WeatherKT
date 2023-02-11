package com.example.weather.ui.model

data class CurrentWeatherUi(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    val day: String,
    val time: String,
    var name: String
) {
    data class Weather(
        val main: String,
        var description: String,
        val icon: String
    )

    data class Main(
        var temp: String,
        var feelsLike: String,
        var pressure: String,
        var humidity: String
    )

    data class Wind(var speed: String)

    data class Sys(
        var sunrise: String,
        var sunset: String
    )
}
