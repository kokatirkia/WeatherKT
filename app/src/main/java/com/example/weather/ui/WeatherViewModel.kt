package com.example.weather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.repository.WeatherRepository
import com.example.weather.utils.Constants
import com.example.weather.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {

    private lateinit var currentWeather: LiveData<CurrentWeatherEntity>
    private lateinit var extendedWeather: LiveData<ExtendedWeatherEntity>
    private var apiResponseMessage: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            currentWeather = weatherRepository.geCurrentWeather().asLiveData()
            extendedWeather = weatherRepository.geExtendedWeather().asLiveData()
            Constants.CITY = weatherRepository.getCityFromPreferences()
        }
    }

    fun fetchWeatherData() = viewModelScope.launch(Dispatchers.IO) {
        if (networkHelper.isNetworkConnected()) {
            val currentWeather = weatherRepository.getCurrentWeatherFromApi()
            if (currentWeather.isSuccessful) {
                currentWeather.body()?.let { CurrentWeatherEntity(1, it) }
                    ?.let {
                        weatherRepository.insertCurrentWeather(it)
                    }
                apiResponseMessage.postValue("Data Updated!")
            } else apiResponseMessage.postValue("City not found!")

            val extendedWeather = weatherRepository.getExtendedWeatherFromApi()
            if (extendedWeather.isSuccessful) {
                extendedWeather.body()?.let { ExtendedWeatherEntity(1, it) }
                    ?.let {
                        weatherRepository.insertExtendedWeather(it)
                    }
            }
        } else apiResponseMessage.postValue("No internet connection!")
    }

    fun getCurrentWeather(): LiveData<CurrentWeatherEntity> = currentWeather

    fun getExtendedWeather(): LiveData<ExtendedWeatherEntity> = extendedWeather

    fun getApiResponseMessage(): LiveData<String> = apiResponseMessage

    fun saveCityInPreferences(city: String) {
        weatherRepository.saveCityInPreferences(city)
    }
}