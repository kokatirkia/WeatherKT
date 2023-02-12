package com.example.weather.data.repository

import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.localdatabase.preferences.WeatherPreferences
import com.example.weather.data.networking.WeatherApi
import com.example.weather.data.networking.model.CurrentWeatherResponse
import com.example.weather.data.networking.model.ExtendedWeatherResponse
import com.example.weather.data.networking.model.WeatherResponse
import com.example.weather.data.repository.mapper.toWeatherDomain
import com.example.weather.data.repository.mapper.toWeatherEntity
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryImplTest {
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var currentWeatherResponse: CurrentWeatherResponse
    private lateinit var extendedWeatherResponse: ExtendedWeatherResponse
    private val cityName = "Tbilisi"

    @Mock
    private lateinit var weatherDao: WeatherDao

    @Mock
    private lateinit var weatherApi: WeatherApi

    @Mock
    private lateinit var weatherPreferences: WeatherPreferences

    @ExperimentalCoroutinesApi
    @Before
    fun setup() = runTest {
        currentWeatherResponse = WeatherApiFactory.makeCurrentWeatherResponse()
        extendedWeatherResponse = WeatherApiFactory.makeExtendedWeatherResponse()
        whenever(weatherApi.getCurrentWeather(any(), any(), any())).thenReturn(
            currentWeatherResponse
        )
        whenever(weatherApi.getExtendedWeather(any(), any(), any())).thenReturn(
            extendedWeatherResponse
        )
        whenever(weatherPreferences.getCityName()).thenReturn(cityName)
        weatherRepository = WeatherRepositoryImpl(weatherDao, weatherApi, weatherPreferences)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi when city name is not null or empty should call preferences saveCityName`() =
        runTest {
            weatherRepository.fetchWeatherFromApi(cityName)
            verify(weatherPreferences).saveCityName(cityName)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi when city name is null should not call preferences saveCityName`() =
        runTest {
            weatherRepository.fetchWeatherFromApi(null)
            verify(weatherPreferences, never()).saveCityName(any())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi when city name is empty should not call preferences saveCityName`() =
        runTest {
            val city = ""
            weatherRepository.fetchWeatherFromApi(city)
            verify(weatherPreferences, never()).saveCityName(city)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi when city name is null should call preferences getCityName()`() =
        runTest {
            weatherRepository.fetchWeatherFromApi(null)
            verify(weatherPreferences).getCityName()
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi when city name is empty should call preferences getCityName()`() =
        runTest {
            weatherRepository.fetchWeatherFromApi("")
            verify(weatherPreferences).getCityName()
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi when city name is not null or empty should not call preferences getCityName()`() =
        runTest {
            weatherRepository.fetchWeatherFromApi(cityName)
            verify(weatherPreferences, never()).getCityName()
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi should call api methods`() = runTest {
        weatherRepository.fetchWeatherFromApi(null)
        inOrder(weatherApi) {
            verify(weatherApi).getCurrentWeather(any(), any(), any())
            verify(weatherApi).getExtendedWeather(any(), any(), any())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApi should return weather mapped to domain`() = runTest {
        val weather =
            WeatherResponse(currentWeatherResponse, extendedWeatherResponse).toWeatherDomain()
        val returnedWeather = weatherRepository.fetchWeatherFromApi(null)
        assertEquals(returnedWeather, weather)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `saveWeatherInLocalDatabase should map weather to entity and call weatherDao insertWeather`() =
        runTest {
            val weather = WeatherFactory.makeWeather()
            weatherRepository.saveWeatherInLocalDatabase(weather)
            verify(weatherDao).insertWeather(weather.toWeatherEntity())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `getWeatherFromLocalDatabase should call weatherDao getWeather`() = runTest {
        weatherRepository.getWeatherFromLocalDatabase()
        verify(weatherDao).getWeather()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getWeatherFromLocalDatabase should return weather mapped to domain`() = runTest {
        val weatherEntity = WeatherEntityFactory.makeWeatherEntity()
        val weatherDomain = weatherEntity.toWeatherDomain()

        whenever(weatherDao.getWeather()).thenReturn(weatherEntity)
        val returnedWeather = weatherRepository.getWeatherFromLocalDatabase()

        assertEquals(returnedWeather, weatherDomain)
    }
}