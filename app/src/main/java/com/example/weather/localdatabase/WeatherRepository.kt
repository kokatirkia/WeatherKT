package com.example.weather.localdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.localdatabase.model.CurrentWeather
import com.example.weather.localdatabase.model.ExtendedWeather
import com.example.weather.networking.WeatherApi
import com.example.weather.networking.model.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) {

    private val apiResponseMessage = MutableLiveData<String>()

    suspend fun getWeatherFromApi() {
        lateinit var responseMessage: String

        val currentWeather =
            weatherApi.getWeather(Constants.CITY, Constants.units, Constants.ApiKey)
        if (currentWeather.isSuccessful) {
            currentWeather.body()?.let { CurrentWeather(1, it) }
                ?.let {
                    insertCurrentWeather(it)
                }
            responseMessage = "success"
        } else responseMessage = "!success"

        val extendedWeather =
            weatherApi.getWeatherLong(Constants.CITY, Constants.units, Constants.ApiKey)
        if (extendedWeather.isSuccessful) {
            extendedWeather.body()?.let { ExtendedWeather(1, it) }
                ?.let {
                    insertExtendedWeather(it)
                }
        }
        withContext(Dispatchers.Main) {
            apiResponseMessage.value = responseMessage
        }
    }

    private suspend fun insertCurrentWeather(currentWeather: CurrentWeather) {
        weatherDao.insertCurrentWeather(currentWeather)
    }

    private suspend fun insertExtendedWeather(extendedWeather: ExtendedWeather) {
        weatherDao.insertExtendedWeather(extendedWeather)
    }

    fun getCurrentWeather(): LiveData<CurrentWeather> {
        return weatherDao.getCurrentWeather()
    }

    fun getExtendedWeather(): LiveData<ExtendedWeather> {
        return weatherDao.getExtendedWeather()
    }

    fun getApiResponseMessage(): LiveData<String> {
        return apiResponseMessage
    }
}