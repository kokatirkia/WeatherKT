package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecases.FetchWeatherFromApiUseCase
import com.example.weather.domain.usecases.FetchWeatherFromLocalSourceUseCase
import com.example.weather.ui.model.WeatherState
import com.example.weather.ui.model.WeatherUi
import com.example.weather.ui.model.mapper.toWeatherUi
import com.example.weather.ui.screens.WeatherTabScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val fetchWeatherFromApiUseCase: FetchWeatherFromApiUseCase,
    private val fetchWeatherFromLocalSourceUseCase: FetchWeatherFromLocalSourceUseCase,
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

    fun fetchWeatherData() {
        _weatherState.value = _weatherState.value!!.copy(loading = true)

        viewModelScope.launch {
            val errorMessage: String? =
                fetchWeatherFromApiUseCase.invoke(_cityNameTextFieldValue.value)?.value
            val weatherUi: WeatherUi? =
                fetchWeatherFromLocalSourceUseCase.invoke()?.toWeatherUi()

            _weatherState.value = _weatherState.value!!.copy(
                errorMessage = errorMessage,
                weatherUi = weatherUi,
                loading = false
            )
        }
    }
}