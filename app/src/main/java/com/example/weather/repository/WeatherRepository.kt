package com.example.weather.repository

import android.content.SharedPreferences
import com.example.weather.localdatabase.WeatherDao
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.networking.WeatherApi
import com.example.weather.networking.model.CurrentWeatherData
import com.example.weather.networking.model.ExtendedWeatherData
import com.example.weather.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val sharedPreferences: SharedPreferences
) {
    suspend fun getCurrentWeatherFromApi(): Response<CurrentWeatherData> =
        weatherApi.getCurrentWeather(Constants.CITY, Constants.units, Constants.ApiKey)

    suspend fun getExtendedWeatherFromApi(): Response<ExtendedWeatherData> =
        weatherApi.getExtendedWeather(Constants.CITY, Constants.units, Constants.ApiKey)

    suspend fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity) {
        weatherDao.insertCurrentWeather(currentWeatherEntity)
    }

    suspend fun insertExtendedWeather(extendedWeatherEntity: ExtendedWeatherEntity) {
        weatherDao.insertExtendedWeather(extendedWeatherEntity)
    }

    fun geCurrentWeather(): Flow<CurrentWeatherEntity> {
        return weatherDao.getCurrentWeather()
    }

    fun geExtendedWeather(): Flow<ExtendedWeatherEntity> {
        return weatherDao.getExtendedWeather()
    }

    fun getCityFromPreferences(): String {
        return sharedPreferences.getString("city", "Tbilisi").toString()
    }

    fun saveCityInPreferences(city: String) {
        sharedPreferences.edit().putString("city", city).apply()
    }
}