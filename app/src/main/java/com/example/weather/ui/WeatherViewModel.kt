package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecases.FetchWeatherFromApiUseCase
import com.example.weather.domain.usecases.FetchWeatherFromLocalSourceUseCase
import com.example.weather.ui.model.WeatherState
import com.example.weather.ui.model.mapper.UiWeatherMapper
import com.example.weather.ui.screens.WeatherTabScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    private val fetchWeatherFromApiUseCase: FetchWeatherFromApiUseCase,
    private val fetchWeatherFromLocalSourceUseCase: FetchWeatherFromLocalSourceUseCase,
    private val uiWeatherMapper: UiWeatherMapper
) : ViewModel() {

    private val _weatherState: MutableLiveData<WeatherState> = MutableLiveData(WeatherState())
    val weatherState: LiveData<WeatherState> = _weatherState

    private val _cityNameTextFieldValue: MutableLiveData<String> = MutableLiveData("")
    val cityNameTextFieldValue: LiveData<String> = _cityNameTextFieldValue

    private val _selectedTabScreen: MutableLiveData<WeatherTabScreen> =
        MutableLiveData(WeatherTabScreen.Current)
    val selectedTabScreen: LiveData<WeatherTabScreen> = _selectedTabScreen

    init {
        fetchWeatherData()
    }

    fun onTextFieldValueChanged(newValue: String) {
        _cityNameTextFieldValue.value = newValue
    }

    fun onSelectedTabScreenChanged(newTabScreen: WeatherTabScreen) {
        _selectedTabScreen.value = newTabScreen
    }

    fun fetchWeatherData() = viewModelScope.launch {
        _weatherState.value = _weatherState.value!!.copy(loading = true)

        val errorMessage =
            fetchWeatherFromApiUseCase.invoke(_cityNameTextFieldValue.value)?.value
        val weatherUi = uiWeatherMapper.weatherDomainToWeatherUi(
            fetchWeatherFromLocalSourceUseCase.invoke().first()
        )

        _weatherState.value = _weatherState.value!!.copy(
            errorMessage = errorMessage,
            weatherUi = weatherUi,
            loading = false
        )
    }
}