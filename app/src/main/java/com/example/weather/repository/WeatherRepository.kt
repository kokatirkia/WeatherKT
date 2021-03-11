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
    init {
        Constants.CITY = sharedPreferences.getString("city", "Tbilisi").toString()
    }

    suspend fun fetchWeatherData(city: String?) {
        saveCityInPreferences(city)
        val currentWeather =
            weatherApi.getCurrentWeather(Constants.CITY, Constants.units, Constants.ApiKey)
        weatherDao.insertCurrentWeather(CurrentWeatherEntity(1, currentWeather))
        val extendedWeather =
            weatherApi.getExtendedWeather(Constants.CITY, Constants.units, Constants.ApiKey)
        weatherDao.insertExtendedWeather(ExtendedWeatherEntity(1, extendedWeather))
    }

    fun geCurrentWeather(): Flow<CurrentWeatherEntity> {
        return weatherDao.getCurrentWeather()
    }

    fun geExtendedWeather(): Flow<ExtendedWeatherEntity> {
        return weatherDao.getExtendedWeather()
    }

    private fun saveCityInPreferences(city: String?) {
        if (city != null) Constants.CITY = city
        sharedPreferences.edit().putString("city", city).apply()
    }
}