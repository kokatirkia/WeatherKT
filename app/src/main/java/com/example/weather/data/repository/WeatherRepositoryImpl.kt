package com.example.weather.data.repository

import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.localdatabase.preferences.WeatherPreferences
import com.example.weather.data.networking.WeatherApi
import com.example.weather.data.networking.model.WeatherModelApi
import com.example.weather.data.repository.mapper.toWeatherDomain
import com.example.weather.data.repository.mapper.toWeatherEntity
import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val weatherPreferences: WeatherPreferences
) : WeatherRepository {

    override suspend fun fetchWeatherFromApi(city: String?): Weather {
        val cityName = if (city.isNullOrEmpty()) {
            weatherPreferences.getCityName()
        } else {
            weatherPreferences.saveCityName(city)
            city
        }

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

        return WeatherModelApi(apiCurrentWeather, apiExtendedWeather).toWeatherDomain()
    }

    override suspend fun saveWeatherInLocalDatabase(weather: Weather) {
        weatherDao.insertWeather(weather.toWeatherEntity())
    }

    override suspend fun getWeatherFromLocalDatabase(): Weather? {
        return weatherDao.getWeather()?.toWeatherDomain()
    }
}