package com.example.weather.data.networking

import com.example.weather.data.networking.model.CurrentWeatherApi
import com.example.weather.data.networking.model.ExtendedWeatherApi
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") apiid: String
    ): CurrentWeatherApi

    @GET("forecast")
    suspend fun getExtendedWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") appid: String
    ): ExtendedWeatherApi
}