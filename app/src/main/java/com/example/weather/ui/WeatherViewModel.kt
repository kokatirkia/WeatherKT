package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class WeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val currentWeather = weatherRepository.currentWeather().asLiveData()
    val extendedWeather = weatherRepository.extendedWeather().asLiveData()
    private val _responseMessage: MutableLiveData<String> = MutableLiveData()
    val responseMessage: LiveData<String> = _responseMessage

    init {
        fetchWeatherData(null)
    }

    fun fetchWeatherData(city: String?) = viewModelScope.launch {
        try {
            weatherRepository.fetchWeatherData(city)
            _responseMessage.value = "Data updated"
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> _responseMessage.value = "No internet connection"
                is HttpException -> _responseMessage.value = "City not found!"
                else -> _responseMessage.value = "Error"
            }
        }
    }
}