package com.example.weather.repository

import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun currentWeather(): Flow<CurrentWeatherEntity>

    fun extendedWeather(): Flow<ExtendedWeatherEntity>

    suspend fun fetchWeatherData(city: String?)

    fun saveCityInPreferences(city: String?)
}