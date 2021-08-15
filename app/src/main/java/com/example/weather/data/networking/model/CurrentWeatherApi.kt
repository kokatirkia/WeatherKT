package com.example.weather.data.networking.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherApi(
    @SerializedName("weather")
    val weather: List<WeatherApi>,
    @SerializedName("main")
    val main: MainApi,
    @SerializedName("wind")
    val wind: WindApi,
    @SerializedName("sys")
    val sys: SysApi,
    @SerializedName("dt")
    val dt:Long,
    @SerializedName("name")
    var name: String
)

data class WeatherApi(
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    val icon: String
)

data class MainApi(
    @SerializedName("temp")
    var temp: String,
    @SerializedName("feels_like")
    var feelsLike: String,
    @SerializedName("pressure")
    var pressure: Int,
    @SerializedName("humidity")
    var humidity: Int
)

data class WindApi(
    @SerializedName("speed")
    var speed: Double
)

data class SysApi(
    @SerializedName("sunrise")
    var sunrise: Long,
    @SerializedName("sunset")
    var sunset: Long
)