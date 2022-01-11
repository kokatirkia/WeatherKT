package com.example.weather.data.repository

import android.content.SharedPreferences
import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.networking.WeatherApi
import com.example.weather.data.networking.model.CurrentWeatherApi
import com.example.weather.data.networking.model.ExtendedWeatherApi
import com.example.weather.data.networking.model.WeatherModelApi
import com.example.weather.data.repository.mapper.toWeatherDomain
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

    private lateinit var currentWeatherApi: CurrentWeatherApi
    private lateinit var extendedWeatherApi: ExtendedWeatherApi

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
        currentWeatherApi = WeatherApiFactory.makeCurrentWeatherApi()
        extendedWeatherApi = WeatherApiFactory.makeExtendedWeatherApi()
        whenever(weatherApi.getCurrentWeather(any(), any(), any())).thenReturn(currentWeatherApi)
        whenever(weatherApi.getExtendedWeather(any(), any(), any())).thenReturn(extendedWeatherApi)
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
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

    @Test
    fun getCityFromPreferences_shouldGetCityFromPreferences() {
        repository.getCityFromPreferences()
        verify(sharedPreferences).getString(any(), any())
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
    fun fetchWeatherFromApi_whenCityNameIsNotNullOrEmptyShouldSaveInPreferences() =
        runBlockingTest {
            val city = "Tbilisi"
            spyRepository.fetchWeatherFromApi(city)
            verify(spyRepository).saveCityInPreferences(city)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsNullShouldNotSaveInPreferences() = runBlockingTest {
        spyRepository.fetchWeatherFromApi(null)
        verify(spyRepository, never()).saveCityInPreferences(null)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsEmptyShouldNotSaveInPreferences() = runBlockingTest {
        val city = ""
        spyRepository.fetchWeatherFromApi(city)
        verify(spyRepository, never()).saveCityInPreferences(city)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsNullShouldGetFromPreferences() = runBlockingTest {
        spyRepository.fetchWeatherFromApi(null)
        verify(spyRepository).getCityFromPreferences()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsEmptyShouldGetFromPreferences() = runBlockingTest {
        spyRepository.fetchWeatherFromApi("")
        verify(spyRepository).getCityFromPreferences()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_whenCityNameIsNotNullOrEmptyShouldNotGetFromPreferences() =
        runBlockingTest {
            spyRepository.fetchWeatherFromApi("test")
            verify(spyRepository, never()).getCityFromPreferences()
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

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherFromApi_shouldReturnWeatherMappedToDomain() = runBlockingTest {
        val weather = WeatherModelApi(currentWeatherApi, extendedWeatherApi).toWeatherDomain()
        val returnedWeather = spyRepository.fetchWeatherFromApi(null)
        assert(returnedWeather == weather)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getWeatherFromLocalDatabase_shouldCallWeatherDaoGetWeather() = runBlockingTest {
        repository.getWeatherFromLocalDatabase()
        verify(weatherDao).getWeather()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getWeatherFromLocalDatabase_shouldReturnWeatherDomain() = runBlockingTest {
        val weatherEntity = WeatherEntityFactory.makeWeatherEntity()
        val weatherDomain = weatherEntity.toWeatherDomain()

        whenever(weatherDao.getWeather()).thenReturn(weatherEntity)
        val returnedWeather = repository.getWeatherFromLocalDatabase()

        assert(returnedWeather == weatherDomain)
    }
}