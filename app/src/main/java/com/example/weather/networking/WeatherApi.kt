package com.example.weather.networking

import com.example.weather.networking.model.CurrentWeatherData
import com.example.weather.networking.model.ExtendedWeatherData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") apiid: String
    ): Response<CurrentWeatherData>

    @GET("forecast")
    suspend fun getWeatherLong(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") appid: String
    ): Response<ExtendedWeatherData>

}