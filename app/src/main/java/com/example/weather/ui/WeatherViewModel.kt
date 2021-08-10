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

    private val _cityNameTextFieldValue: MutableLiveData<String> = MutableLiveData("")
    val cityNameTextFieldValue: LiveData<String> = _cityNameTextFieldValue

    private val _selectedTabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTabIndex: LiveData<Int> = _selectedTabIndex

    init {
        fetchWeatherData()
    }

    fun onTextFieldValueChanged(newValue: String) {
        _cityNameTextFieldValue.value = newValue
    }

    fun onSelectedIndexChanged(newIndex: Int) {
        _selectedTabIndex.value = newIndex
    }

    fun fetchWeatherData() = viewModelScope.launch {
        if (_cityNameTextFieldValue.value.isNullOrEmpty()) {
            _weatherState.value = fetchWeatherUseCase.invoke()
        } else {
            _weatherState.value = fetchWeatherUseCase.invoke(_cityNameTextFieldValue.value)
        }
    }
}