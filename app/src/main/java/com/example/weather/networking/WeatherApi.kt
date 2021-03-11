package com.example.weather.networking

import com.example.weather.networking.model.CurrentWeatherData
import com.example.weather.networking.model.ExtendedWeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") apiid: String
    ): CurrentWeatherData

    @GET("forecast")
    suspend fun getExtendedWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") appid: String
    ): ExtendedWeatherData
}