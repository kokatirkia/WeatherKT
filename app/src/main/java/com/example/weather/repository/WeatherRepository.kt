package com.example.weather.repository

import android.content.SharedPreferences
import com.example.weather.localdatabase.WeatherDao
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.networking.WeatherApi
import com.example.weather.utils.Constants
import com.example.weather.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val sharedPreferences: SharedPreferences,
    private val networkHelper: NetworkHelper
) {
    init {
        Constants.CITY = sharedPreferences.getString("city", "Tbilisi").toString()
    }

    private val responseFlow = MutableSharedFlow<String>()

    suspend fun fetchWeatherData(city: String?) {
        saveCityInPreferences(city)
        if (networkHelper.isNetworkConnected()) {
            val currentWeather =
                weatherApi.getCurrentWeather(Constants.CITY, Constants.units, Constants.ApiKey)
            if (currentWeather.isSuccessful) {
                currentWeather.body()?.let { CurrentWeatherEntity(1, it) }
                    ?.let {
                        weatherDao.insertCurrentWeather(it)
                        responseFlow.emit("Data Updated!")
                    }
            } else responseFlow.emit("City not found!")

            val extendedWeather =
                weatherApi.getExtendedWeather(Constants.CITY, Constants.units, Constants.ApiKey)
            if (extendedWeather.isSuccessful) {
                extendedWeather.body()?.let { ExtendedWeatherEntity(1, it) }
                    ?.let {
                        weatherDao.insertExtendedWeather(it)
                    }
            }
        } else responseFlow.emit("No internet connection!")
    }

    fun getFlow(): MutableSharedFlow<String> {
        return responseFlow
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