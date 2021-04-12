package com.example.weather.domain.usecases

import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Weather> {
        return repository.getWeather().filterNotNull()
    }
}