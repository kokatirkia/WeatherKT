package com.example.weather.domain.repository

import com.example.weather.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getWeatherFromLocalDatabase(): Flow<Weather>

    suspend fun saveWeatherInLocalDatabase(weather: Weather)

    suspend fun fetchWeatherFromApi(city: String?): Weather

    fun saveCityInPreferences(city: String?)
}