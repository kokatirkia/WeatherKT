package com.example.weather.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.weather.data.localdatabase.WeatherDao
import com.example.weather.data.localdatabase.WeatherDb
import com.example.weather.data.localdatabase.preferences.WeatherPreferencesImpl
import com.example.weather.data.networking.WeatherApi
import com.example.weather.data.networking.model.WeatherResponse
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.data.repository.mapper.toWeatherDomain
import com.example.weather.domain.usecases.FetchWeatherFromApiUseCase
import com.example.weather.domain.usecases.FetchWeatherFromLocalSourceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelIntegrationTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var weatherApi: WeatherApi
    private lateinit var weatherDao: WeatherDao
    private lateinit var weatherPreferences: WeatherPreferencesImpl
    private lateinit var weatherRepository: WeatherRepositoryImpl
    private lateinit var fetchWeatherFromApiUseCase: FetchWeatherFromApiUseCase
    private lateinit var fetchWeatherFromLocalSourceUseCase: FetchWeatherFromLocalSourceUseCase
    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun before() {
        weatherDao = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDb::class.java
        ).allowMainThreadQueries().build().weatherDao()
        weatherPreferences = WeatherPreferencesImpl(
            InstrumentationRegistry.getInstrumentation().context.getSharedPreferences("test", 0)
        )
        weatherRepository = WeatherRepositoryImpl(weatherDao, weatherApi, weatherPreferences)
        fetchWeatherFromApiUseCase = FetchWeatherFromApiUseCase(weatherRepository)
        fetchWeatherFromLocalSourceUseCase = FetchWeatherFromLocalSourceUseCase(weatherRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cityNameIsSavedInPreferences() = runTest {
        val cityName = "London"
        weatherViewModel = WeatherViewModel(
            fetchWeatherFromApiUseCase,
            fetchWeatherFromLocalSourceUseCase
        )
        weatherViewModel.onTextFieldValueChanged(cityName)
        weatherViewModel.fetchWeatherData()
        assertEquals(cityName, weatherPreferences.getCityName())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchWeatherDataSavesApiDataInLocalDatabase() = runTest {
        val currentWeatherResponse = WeatherApiFactory.makeCurrentWeatherResponse()
        val extendedWeatherApi = WeatherApiFactory.makeExtendedWeatherApi()
        whenever(weatherApi.getCurrentWeather(any(), any(), any())).thenReturn(currentWeatherResponse)
        whenever(weatherApi.getExtendedWeather(any(), any(), any())).thenReturn(extendedWeatherApi)
        weatherViewModel = WeatherViewModel(
            fetchWeatherFromApiUseCase,
            fetchWeatherFromLocalSourceUseCase
        )
        weatherViewModel.fetchWeatherData()
        assertEquals(
            weatherDao.getWeather()?.toWeatherDomain(),
            WeatherResponse(currentWeatherResponse, extendedWeatherApi).toWeatherDomain()
        )
    }
}