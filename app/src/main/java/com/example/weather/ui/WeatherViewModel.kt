package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.localdatabase.WeatherRepository
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private var currentWeather: LiveData<CurrentWeatherEntity> = weatherRepository.getCurrentWeather()
    private var extendedWeather: LiveData<ExtendedWeatherEntity> = weatherRepository.getExtendedWeather()
    private var apiResponseMessage: LiveData<String> = weatherRepository.getApiResponseMessage()

    fun fetchWeatherData() = viewModelScope.launch(Dispatchers.IO) {
        weatherRepository.getWeatherFromApi()
    }

    fun getCurrentWeather(): LiveData<CurrentWeatherEntity> = currentWeather

    fun getExtendedWeather(): LiveData<ExtendedWeatherEntity> = extendedWeather

    fun getApiResponseMessage(): LiveData<String> = apiResponseMessage
}