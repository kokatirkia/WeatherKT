package com.example.weather.domain.usecases

import com.example.weather.domain.repository.WeatherRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

enum class ErrorMessageEnum(val value: String) {
    NoInternetConnection("No Internet Connection"),
    CityNotFound("City not found!"),
    ErrorWhileFetching("Error fetching data"),
}

class FetchWeatherFromApiUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(city: String?): ErrorMessageEnum? {
        return try {
            val weather = weatherRepository.fetchWeatherFromApi(city)
            weatherRepository.saveWeatherInLocalDatabase(weather)
            null
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ErrorMessageEnum.NoInternetConnection
                is HttpException -> ErrorMessageEnum.CityNotFound
                else -> ErrorMessageEnum.ErrorWhileFetching
            }
        }
    }
}