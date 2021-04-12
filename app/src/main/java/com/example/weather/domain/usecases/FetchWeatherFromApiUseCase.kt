package com.example.weather.domain.usecases

import com.example.weather.domain.repository.Repository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchWeatherFromApiUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(city: String?): String {
        return try {
            repository.fetchWeatherData(city)
            "Data updated"
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> "No internet connection"
                is HttpException -> "City not found!"
                else -> "Error"
            }
        }
    }
}