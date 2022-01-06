package com.example.weather.data.repository

import android.content.SharedPreferences
import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.networking.WeatherApi
import com.example.weather.data.repository.mapper.toWeatherEntity
import com.example.weather.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {
    private lateinit var repository: Repository

    private lateinit var spyRepository: Repository

    @Mock
    private lateinit var weatherDao: WeatherDao

    @Mock
    private lateinit var weatherApi: WeatherApi

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @ExperimentalCoroutinesApi
    @Before
    fun setup() = runBlockingTest {
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        whenever(
            weatherApi.getCurrentWeather(any(), any(), any())
        ).thenReturn(WeatherApiFactory.makeCurrentWeatherApi())
        whenever(
            weatherApi.getExtendedWeather(any(), any(), any())
        ).thenReturn(WeatherApiFactory.makeExtendedWeatherApi())
        repository = WeatherRepository(weatherDao, weatherApi, sharedPreferences)
        spyRepository = spy(repository)
    }

    @Test
    fun saveCityInPreferences_shouldSaveInPreferences() {
        val city = "cityName"
        repository.saveCityInPreferences(city)
        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putString(any(), eq(city))
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun saveCityInPreferences_shouldNotSaveInPreferencesIfNull() {
        val city: String? = null
        repository.saveCityInPreferences(city)
        verify(sharedPreferencesEditor, never()).putString(any(), eq(city))
    }

    @Test
    fun saveCityInPreferences_shouldNotSaveInPreferencesIfEmpty() {
        val city = ""
        repository.saveCityInPreferences(city)
        verify(sharedPreferencesEditor, never()).putString(any(), eq(city))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun saveWeatherInLocalDatabase_shouldMapWeatherToEntityAndCallWeatherDaoInsertWeather() =
        runBlockingTest {
            val weather = WeatherFactory.makeWeather()
            repository.saveWeatherInLocalDatabase(weather)
            verify(weatherDao).insertWeather(weather.toWeatherEntity())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_shouldCallSaveCityInPreferences() = runBlockingTest {
        spyRepository.fetchWeatherFromApi(null)
        verify(spyRepository).saveCityInPreferences(null)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsNullShouldGetFromPreferences() = runBlockingTest {
        spyRepository.fetchWeatherFromApi(null)
        verify(sharedPreferences).getString(any(), any())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsEmptyShouldGetFromPreferences() = runBlockingTest {
        spyRepository.fetchWeatherFromApi("")
        verify(sharedPreferences).getString(any(), any())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsNotNullOrEmptyShouldNotGetFromPreferences() =
        runBlockingTest {
            spyRepository.fetchWeatherFromApi("test")
            verify(sharedPreferences, never()).getString(any(), any())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_shouldCallApiMethods() = runBlockingTest {
        spyRepository.fetchWeatherFromApi(null)
        inOrder(weatherApi) {
            verify(weatherApi).getCurrentWeather(any(), any(), any())
            verify(weatherApi).getExtendedWeather(any(), any(), any())
        }
    }
}