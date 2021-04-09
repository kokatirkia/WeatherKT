package com.example.weather.data.repository

import android.content.SharedPreferences
import com.example.weather.utils.WeatherMapper
import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.Repository
import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.networking.WeatherApi
import com.example.weather.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val sharedPreferences: SharedPreferences,
    private val weatherMapper: WeatherMapper
) : Repository {

    override fun getWeather(): Flow<Weather> {
        return weatherDao.getWeather().filterNotNull()
            .map { weatherMapper.weatherEntityToWeatherDomain(it) }
    }

    override suspend fun fetchWeatherData(city: String?) {
        saveCityInPreferences(city)

        val cityName = city ?: sharedPreferences.getString("city", "Tbilisi").toString()

        val apiCurrentWeather = weatherApi.getCurrentWeather(
            cityName,
            Constants.units,
            Constants.ApiKey
        )
        val apiExtendedWeather = weatherApi.getExtendedWeather(
            cityName,
            Constants.units,
            Constants.ApiKey
        )

        weatherDao.insertWeather(
            weatherMapper.weatherApiToWeatherEntity(apiCurrentWeather, apiExtendedWeather)
        )
    }

    override fun saveCityInPreferences(city: String?) {
        city?.let {
            sharedPreferences.edit().putString("city", it).apply()
        }
    }
}