package com.example.weather.domain.repository

import com.example.weather.domain.model.Weather

interface Repository {
    suspend fun getWeatherFromLocalDatabase(): Weather?

    suspend fun saveWeatherInLocalDatabase(weather: Weather)

    suspend fun fetchWeatherFromApi(city: String?): Weather

    fun getCityFromPreferences(): String

    fun saveCityInPreferences(city: String?)
}