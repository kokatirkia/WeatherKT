package com.example.weather.domain.repository

import com.example.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun fetchWeatherFromApi(city: String?): Weather
    suspend fun saveWeatherInLocalDatabase(weather: Weather)
    suspend fun getWeatherFromLocalDatabase(): Weather?
}