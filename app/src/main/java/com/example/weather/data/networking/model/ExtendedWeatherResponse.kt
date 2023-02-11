package com.example.weather.data.networking.model

import com.google.gson.annotations.SerializedName

data class ExtendedWeatherResponse(
    @SerializedName("message")
    val message: Double,
    @SerializedName("list")
    val list: List<WeatherItem>
) {
    data class WeatherItem(
        @SerializedName("dt")
        val dt: Long,
        @SerializedName("main")
        val main: Main,
        @SerializedName("dt_txt")
        val dtTxt: String,
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("wind")
        val wind: Wind
    )

    data class Main(
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("humidity")
        val humidity: Int
    )

    data class Weather(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String
    )

    data class Wind(
        @SerializedName("speed")
        val speed: Double
    )
}