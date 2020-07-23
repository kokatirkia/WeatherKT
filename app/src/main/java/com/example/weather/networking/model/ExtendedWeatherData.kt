package com.example.weather.networking.model

data class ExtendedWeatherData(
    val message: Double,
    val list: List<ExtendedWeather>
)

data class ExtendedWeather(
    val dt: Long,
    val main: ExtendedMain,
    val dt_txt: String,
    val weather: List<Weath>,
    val wind: WindRec,
    var expandable: Boolean = false
)

data class WindRec(val speed: Double)

data class Weath(
    val description: String,
    val icon: String
)

data class ExtendedMain(
    val temp: Double,
    val pressure: String,
    val humidity: String
)