package com.example.weather.domain.usecases

import com.example.weather.data.repository.WeatherFactory
import com.example.weather.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
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
    private lateinit var repository: Repository
    private lateinit var fetchWeatherFromLocalSourceUseCase: FetchWeatherFromLocalSourceUseCase

    @Before
    fun setup() {
        fetchWeatherFromLocalSourceUseCase = FetchWeatherFromLocalSourceUseCase(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromLocalSourceUseCase_shouldCallRepositoryGetWeatherFromLocalDatabase() =
        runBlockingTest {
            val weatherDomain = WeatherFactory.makeWeather()
            val weatherDomainFlow = flow { emit(weatherDomain) }
            whenever(repository.getWeatherFromLocalDatabase()).thenReturn(weatherDomainFlow)

            val returnedWeather = fetchWeatherFromLocalSourceUseCase.invoke()

            verify(repository).getWeatherFromLocalDatabase()
            assert(returnedWeather.first() == weatherDomainFlow.first())
        }
}