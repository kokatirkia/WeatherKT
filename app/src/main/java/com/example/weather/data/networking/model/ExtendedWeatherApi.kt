package com.example.weather.data.networking.model

import com.google.gson.annotations.SerializedName

data class ExtendedWeatherApi(
    @SerializedName("message")
    val message: Double,
    @SerializedName("list")
    val list: List<WeatherDataApi>
)

data class WeatherDataApi(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("main")
    val main: MainExtendedApi,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("weather")
    val weather: List<DescriptionExtendedApi>,
    @SerializedName("wind")
    val wind: WindExtendedApi
)

data class WindExtendedApi(
    @SerializedName("speed")
    val speed: Double
    )

data class DescriptionExtendedApi(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

data class MainExtendedApi(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)