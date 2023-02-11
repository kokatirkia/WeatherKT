package com.example.weather.data.networking

import com.example.weather.data.networking.model.CurrentWeatherResponse
import com.example.weather.data.networking.model.ExtendedWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") apiid: String
    ): CurrentWeatherResponse

    @GET("forecast")
    suspend fun getExtendedWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") appid: String
    ): ExtendedWeatherResponse
}