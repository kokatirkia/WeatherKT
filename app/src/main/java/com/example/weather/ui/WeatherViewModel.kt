package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class WeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private lateinit var currentWeather: LiveData<CurrentWeatherEntity>
    private lateinit var extendedWeather: LiveData<ExtendedWeatherEntity>
    private val responseMessage: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            currentWeather = weatherRepository.geCurrentWeather().asLiveData()
            extendedWeather = weatherRepository.geExtendedWeather().asLiveData()
        }
    }

    fun fetchWeatherData(city: String?) = viewModelScope.launch {
        try {
            weatherRepository.fetchWeatherData(city)
            responseMessage.value = "Data updated"
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> responseMessage.value = "No internet connection"
                is HttpException -> responseMessage.value = "City not found!"
                else -> responseMessage.value = "Error"
            }
        }
    }

    fun getCurrentWeather(): LiveData<CurrentWeatherEntity> = currentWeather

    fun getExtendedWeather(): LiveData<ExtendedWeatherEntity> = extendedWeather

    fun getResponseMessage(): LiveData<String> = responseMessage
}