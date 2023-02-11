package com.example.weather.domain.model

data class ExtendedWeather(
    val list: List<WeatherItem>
){
    data class WeatherItem(
        val dt: Long,
        val main: Main,
        val dtTxt: String,
        val weather: List<Weather>,
        val wind: Wind
    )

    data class Main(
        val temp: Double,
        val pressure: Int,
        val humidity: Int
    )

    data class Weather(
        val description: String,
        val icon: String
    )

    data class Wind(val speed: Double)

}





