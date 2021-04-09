package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weather.domain.usecases.FetchWeatherFromApiUseCase
import com.example.weather.domain.usecases.GetWeatherUseCase
import com.example.weather.ui.model.WeatherUi
import com.example.weather.ui.model.mapper.UiWeatherMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    getWeatherUseCase: GetWeatherUseCase,
    private val fetchWeatherFromApiUseCase: FetchWeatherFromApiUseCase,
    private val uiWeatherMapper: UiWeatherMapper
) : ViewModel() {

    val weather: LiveData<WeatherUi> = getWeatherUseCase.getWeather().map {
        uiWeatherMapper.weatherDomainToWeatherUi(it)
    }.asLiveData()

    private val _responseMessage: MutableLiveData<String> = MutableLiveData()
    val responseMessage: LiveData<String> = _responseMessage

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData(city: String? = null) = viewModelScope.launch {
        _responseMessage.value = fetchWeatherFromApiUseCase.fetchWeather(city)
    }
}