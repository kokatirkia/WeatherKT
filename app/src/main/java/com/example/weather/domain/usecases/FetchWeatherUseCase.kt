package com.example.weather.domain.usecases

import com.example.weather.domain.model.FetchWeatherResponse
import com.example.weather.domain.repository.Repository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchWeatherUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(city: String?): FetchWeatherResponse {
        val fetchWeatherResponse = FetchWeatherResponse()
        try {
            val weather = repository.fetchWeatherFromApi(city)
            repository.saveWeatherInLocalDatabase(weather)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> fetchWeatherResponse.noInternetConnection = true
                is HttpException -> {
                    fetchWeatherResponse.errorWhileFetching = true
                    fetchWeatherResponse.responseMessage = "City not found!"
                }
                else -> {
                    fetchWeatherResponse.errorWhileFetching = true
                    fetchWeatherResponse.responseMessage = "Error fetching data"
                }
            }
        }
        fetchWeatherResponse.weather =
            repository.getWeatherFromLocalDatabase().filterNotNull().first()

        return fetchWeatherResponse
    }
}