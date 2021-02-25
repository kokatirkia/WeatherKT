package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private lateinit var currentWeather: LiveData<CurrentWeatherEntity>
    private lateinit var extendedWeather: LiveData<ExtendedWeatherEntity>
    private lateinit var responseMessage: LiveData<String>

    init {
        viewModelScope.launch {
            currentWeather = weatherRepository.geCurrentWeather().asLiveData()
            extendedWeather = weatherRepository.geExtendedWeather().asLiveData()
            responseMessage = weatherRepository.getFlow().asLiveData()
        }
    }

    fun fetchWeatherData(city: String?) = viewModelScope.launch(Dispatchers.IO) {
        weatherRepository.fetchWeatherData(city)
    }

    fun getCurrentWeather(): LiveData<CurrentWeatherEntity> = currentWeather

    fun getExtendedWeather(): LiveData<ExtendedWeatherEntity> = extendedWeather

    fun getResponseMessage(): LiveData<String> = responseMessage
}