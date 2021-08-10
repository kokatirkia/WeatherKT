package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecases.FetchWeatherUseCase
import com.example.weather.ui.model.WeatherState
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    private val fetchWeatherUseCase: FetchWeatherUseCase,
) : ViewModel() {

    private val _weatherState: MutableLiveData<WeatherState> = MutableLiveData()
    val weatherState: LiveData<WeatherState> = _weatherState

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData(city: String? = null) = viewModelScope.launch {
        _weatherState.value = fetchWeatherUseCase.invoke(city)
    }
}