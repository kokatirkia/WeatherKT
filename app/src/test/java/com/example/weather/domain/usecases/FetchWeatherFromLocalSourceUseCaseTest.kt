package com.example.weather.domain.usecases

import com.example.weather.data.repository.WeatherFactory
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class FetchWeatherFromLocalSourceUseCaseTest {
    @Mock
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var fetchWeatherFromLocalSourceUseCase: FetchWeatherFromLocalSourceUseCase

    @Before
    fun setup() {
        fetchWeatherFromLocalSourceUseCase = FetchWeatherFromLocalSourceUseCase(weatherRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromLocalSourceUseCase should call repository getWeatherFromLocalDatabase`() =
        runTest {
            val weatherDomain = WeatherFactory.makeWeather()
            whenever(weatherRepository.getWeatherFromLocalDatabase()).thenReturn(weatherDomain)

            val returnedWeather = fetchWeatherFromLocalSourceUseCase.invoke()

            verify(weatherRepository).getWeatherFromLocalDatabase()
            assertEquals(returnedWeather, weatherDomain)
        }
}