package com.example.weather.domain.repository

import com.example.weather.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getWeather(): Flow<Weather>

    suspend fun fetchWeatherData(city: String?)

    fun saveCityInPreferences(city: String?)
}