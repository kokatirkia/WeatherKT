package com.example.weather.data.networking.model

data class ExtendedWeatherApi(
    val message: Double,
    val list: List<WeatherDataApi>
)

data class WeatherDataApi(
    val dt: Long,
    val main: MainExtendedApi,
    val dt_txt: String,
    val weather: List<DescriptionExtendedApi>,
    val wind: WindExtendedApi
)

data class WindExtendedApi(val speed: Double)

data class DescriptionExtendedApi(
    val description: String,
    val icon: String
)

data class MainExtendedApi(
    val temp: Double,
    val pressure: String,
    val humidity: String
)