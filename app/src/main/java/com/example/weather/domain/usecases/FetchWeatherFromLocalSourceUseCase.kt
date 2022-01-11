package com.example.weather.domain.usecases

import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.Repository
import javax.inject.Inject

class FetchWeatherFromLocalSourceUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Weather? {
        return repository.getWeatherFromLocalDatabase()
    }
}