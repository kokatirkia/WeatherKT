package com.example.weather.data.repository

import android.content.SharedPreferences
import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.networking.WeatherApi
import com.example.weather.data.networking.model.WeatherModelApi
import com.example.weather.data.repository.mapper.toWeatherDomain
import com.example.weather.data.repository.mapper.toWeatherEntity
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
) : Repository {
    private val cityKey = "city"

    override fun getWeatherFromLocalDatabase(): Flow<Weather> {
        return weatherDao.getWeather().filterNotNull().map { it.toWeatherDomain() }
    }

    override suspend fun fetchWeatherFromApi(city: String?): Weather {
        val cityName = if (city.isNullOrEmpty()) {
            getCityFromPreferences()
        } else {
            saveCityInPreferences(city)
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

    override fun getCityFromPreferences(): String {
        val defaultCityName = "Tbilisi"
        return sharedPreferences.getString(cityKey, defaultCityName).toString()
    }

    override fun saveCityInPreferences(city: String?) {
        if (!city.isNullOrEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString(cityKey, city)
            editor.apply()
        }
    }
}