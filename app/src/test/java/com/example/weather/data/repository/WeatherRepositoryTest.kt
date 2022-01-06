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

    @Mock
    private lateinit var weatherDao: WeatherDao

    @Mock
    private lateinit var weatherApi: WeatherApi

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Before
    fun setup() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        repository = WeatherRepository(weatherDao, weatherApi, sharedPreferences)
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
}