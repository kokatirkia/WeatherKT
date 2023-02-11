package com.example.weather.ui.model

data class ExtendedWeatherUi(
    val list: List<WeatherItem>
) {
    data class WeatherItem(
        val dt: String,
        val main: Main,
        val dtTxt: String,
        val weather: List<Weather>,
        val wind: Wind,
    )

    data class Main(
        val temp: String,
        val pressure: String,
        val humidity: String
    )

    data class Weather(
        val description: String,
        val icon: String
    )

    data class Wind(val speed: String)
}

