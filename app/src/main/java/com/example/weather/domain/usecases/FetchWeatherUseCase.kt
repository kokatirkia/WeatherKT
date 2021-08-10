package com.example.weather.domain.usecases

import com.example.weather.domain.repository.Repository
import com.example.weather.ui.model.WeatherState
import com.example.weather.ui.model.mapper.UiWeatherMapper
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchWeatherUseCase @Inject constructor(
    private val repository: Repository,
    private val uiWeatherMapper: UiWeatherMapper
) {
    suspend operator fun invoke(city: String? = null): WeatherState {
        val weatherState = WeatherState()
        try {
            repository.fetchWeatherData(city)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> weatherState.noInternetConnection = true
                is HttpException -> {
                    weatherState.errorWhileFetching = true
                    weatherState.responseMessage = "City not found!"
                }
                else -> {
                    weatherState.errorWhileFetching = true
                    weatherState.responseMessage = "Error fetching data"
                }
            }
        }
        weatherState.weatherUi = repository.getWeather().filterNotNull().map {
            uiWeatherMapper.weatherDomainToWeatherUi(it)
        }.first()

        return weatherState
    }
}