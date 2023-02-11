package com.example.weather.data.networking.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("main")
    val main: Main,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("name")
    var name: String
) {
    data class Weather(
        @SerializedName("main")
        val main: String,
        @SerializedName("description")
        var description: String,
        @SerializedName("icon")
        val icon: String
    )

    data class Main(
        @SerializedName("temp")
        var temp: Double,
        @SerializedName("feels_like")
        var feelsLike: String,
        @SerializedName("pressure")
        var pressure: Int,
        @SerializedName("humidity")
        var humidity: Int
    )

    data class Wind(
        @SerializedName("speed")
        var speed: Double
    )

    data class Sys(
        @SerializedName("sunrise")
        var sunrise: Long,
        @SerializedName("sunset")
        var sunset: Long
    )
}