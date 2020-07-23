package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.localdatabase.WeatherRepository
import com.example.weather.localdatabase.model.CurrentWeather
import com.example.weather.localdatabase.model.ExtendedWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    fun fetchWeatherData() = viewModelScope.launch(Dispatchers.IO) {
        weatherRepository.getWeatherFromApi()
    }

    fun getCurrentWeather(): LiveData<CurrentWeather> {
        return weatherRepository.getCurrentWeather()
    }

    fun getExtendedWeather(): LiveData<ExtendedWeather> {
        return weatherRepository.getExtendedWeather()
    }

    fun getApiResponseMessage(): LiveData<String> {
        return weatherRepository.getApiResponseMessage()
    }
}