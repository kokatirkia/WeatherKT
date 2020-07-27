package com.example.weather.localdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.networking.WeatherApi
import com.example.weather.networking.model.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) {

    private var currentWeather: LiveData<CurrentWeatherEntity> = weatherDao.getCurrentWeather()
    private var extendedWeather: LiveData<ExtendedWeatherEntity> = weatherDao.getExtendedWeather()
    private val apiResponseMessage = MutableLiveData<String>()

    suspend fun getWeatherFromApi() {
        lateinit var responseMessage: String

        val currentWeather =
            weatherApi.getWeather(Constants.CITY, Constants.units, Constants.ApiKey)
        if (currentWeather.isSuccessful) {
            currentWeather.body()?.let { CurrentWeatherEntity(1, it) }
                ?.let {
                    insertCurrentWeather(it)
                }
            responseMessage = "success"
        } else responseMessage = "!success"

        val extendedWeather =
            weatherApi.getWeatherLong(Constants.CITY, Constants.units, Constants.ApiKey)
        if (extendedWeather.isSuccessful) {
            extendedWeather.body()?.let { ExtendedWeatherEntity(1, it) }
                ?.let {
                    insertExtendedWeather(it)
                }
        }
        withContext(Dispatchers.Main) {
            apiResponseMessage.value = responseMessage
        }
    }

    private suspend fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity) {
        weatherDao.insertCurrentWeather(currentWeatherEntity)
    }

    private suspend fun insertExtendedWeather(extendedWeatherEntity: ExtendedWeatherEntity) {
        weatherDao.insertExtendedWeather(extendedWeatherEntity)
    }

    fun getCurrentWeather(): LiveData<CurrentWeatherEntity> = currentWeather

    fun getExtendedWeather(): LiveData<ExtendedWeatherEntity> = extendedWeather

    fun getApiResponseMessage(): LiveData<String> = apiResponseMessage
}