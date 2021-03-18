package com.example.weather.repository

import android.content.SharedPreferences
import com.example.weather.localdatabase.WeatherDao
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.networking.WeatherApi
import com.example.weather.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val sharedPreferences: SharedPreferences
) {

    fun currentWeather(): Flow<CurrentWeatherEntity> = weatherDao.getCurrentWeather()

    fun extendedWeather(): Flow<ExtendedWeatherEntity> = weatherDao.getExtendedWeather()

    suspend fun fetchWeatherData(city: String?) {
        saveCityInPreferences(city)

        val cityName = city ?: sharedPreferences.getString("city", "Tbilisi").toString()

        val currentWeather = weatherApi.getCurrentWeather(
            cityName,
            Constants.units,
            Constants.ApiKey
        )
        weatherDao.insertCurrentWeather(CurrentWeatherEntity(1, currentWeather))

        val extendedWeather = weatherApi.getExtendedWeather(
            cityName,
            Constants.units,
            Constants.ApiKey
        )
        weatherDao.insertExtendedWeather(ExtendedWeatherEntity(1, extendedWeather))
    }

    private fun saveCityInPreferences(city: String?) {
        city?.let {
            sharedPreferences.edit().putString("city", it).apply()
        }
    }
}