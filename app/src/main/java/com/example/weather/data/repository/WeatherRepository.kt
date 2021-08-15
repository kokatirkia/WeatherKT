package com.example.weather.data.repository

import android.content.SharedPreferences
import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.networking.WeatherApi
import com.example.weather.data.repository.mapper.WeatherMapper
import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.Repository
import com.example.weather.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val sharedPreferences: SharedPreferences,
    private val weatherMapper: WeatherMapper
) : Repository {

    override fun getWeatherFromLocalDatabase(): Flow<Weather> {
        return weatherDao.getWeather().filterNotNull()
            .map { weatherMapper.weatherEntityToWeatherDomain(it) }
    }

    override suspend fun fetchWeatherFromApi(city: String?): Weather {
        saveCityInPreferences(city)

        val cityName =
            if (city.isNullOrEmpty()) sharedPreferences.getString("city", "Tbilisi").toString()
            else city

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

        return weatherMapper.weatherApiToWeatherDomain(apiCurrentWeather, apiExtendedWeather)

    }

    override suspend fun saveWeatherInLocalDatabase(weather: Weather) {
        weatherDao.insertWeather(weatherMapper.weatherDomainToWeatherEntity(weather))
    }

    override fun saveCityInPreferences(city: String?) {
        if (!city.isNullOrEmpty()) sharedPreferences.edit().putString("city", city).apply()
    }
}