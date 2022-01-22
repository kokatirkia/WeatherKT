package com.example.weather.domain.usecases

import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class FetchWeatherFromLocalSourceUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(): Weather? {
        return weatherRepository.getWeatherFromLocalDatabase()
    }
}