package com.example.weather.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weather.data.repository.WeatherFactory
import com.example.weather.domain.usecases.ErrorMessageEnum
import com.example.weather.domain.usecases.FetchWeatherFromApiUseCase
import com.example.weather.domain.usecases.FetchWeatherFromLocalSourceUseCase
import com.example.weather.ui.model.WeatherState
import com.example.weather.ui.model.mapper.toWeatherUi
import com.example.weather.ui.screens.WeatherTabScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var weatherViewModel: WeatherViewModel

    @Mock
    private lateinit var fetchWeatherFromApiUseCase: FetchWeatherFromApiUseCase

    @Mock
    private lateinit var fetchWeatherFromLocalSourceUseCase: FetchWeatherFromLocalSourceUseCase

    @Mock
    private lateinit var weatherStateObserver: Observer<WeatherState>

    @Mock
    private lateinit var cityNameTextFieldValueObserver: Observer<String>

    @Mock
    private lateinit var selectedTabScreenObserver: Observer<WeatherTabScreen>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        weatherViewModel = WeatherViewModel(
            fetchWeatherFromApiUseCase, fetchWeatherFromLocalSourceUseCase
        )

        weatherViewModel.weatherState.observeForever(weatherStateObserver)
        weatherViewModel.cityNameTextFieldValue.observeForever(cityNameTextFieldValueObserver)
        weatherViewModel.selectedTabScreen.observeForever(selectedTabScreenObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `cityNameTextFieldValue initial value should be empty string`() =
        coroutineRule.runBlockingTest {
            val textFieldValue = weatherViewModel.cityNameTextFieldValue.value

            assertEquals(textFieldValue, "")
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `onTextFieldValueChanged should change value`() = coroutineRule.runBlockingTest {
        val newTextValue = "newCity"
        weatherViewModel.onTextFieldValueChanged(newTextValue)

        verify(cityNameTextFieldValueObserver).onChanged(eq(newTextValue))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `selectedTabScreen initial value should be current`() = coroutineRule.runBlockingTest {
        val tabScreen = weatherViewModel.selectedTabScreen.value

        assertEquals(tabScreen, WeatherTabScreen.Current)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `onSelectedTabScreenChanged should change TabScreen`() = coroutineRule.runBlockingTest {
        val weatherTabScreen = WeatherTabScreen.FiveDays
        weatherViewModel.onSelectedTabScreenChanged(weatherTabScreen)

        verify(selectedTabScreenObserver).onChanged(eq(weatherTabScreen))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherData should change weatherState loading field value to true at start and to false at the end`() =
        coroutineRule.runBlockingTest {
            coroutineRule.pauseDispatcher()

            weatherViewModel.fetchWeatherData()

            assertTrue(weatherViewModel.weatherState.value?.loading ?: false)

            coroutineRule.resumeDispatcher()

            assertFalse(weatherViewModel.weatherState.value?.loading ?: true)

        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherData should return weather data from local source`() =
        coroutineRule.runBlockingTest {
            val weather = WeatherFactory.makeWeather()
            whenever(fetchWeatherFromLocalSourceUseCase.invoke()).thenReturn(weather)
            weatherViewModel.fetchWeatherData()
            assertEquals(weatherViewModel.weatherState.value?.weatherUi, weather.toWeatherUi())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherData weatherState error message should not be null when fetchWeatherFromApiUseCase returns errorMessage`() =
        coroutineRule.runBlockingTest {
            whenever(fetchWeatherFromApiUseCase.invoke(anyString())).thenReturn(ErrorMessageEnum.ErrorWhileFetching)
            weatherViewModel.fetchWeatherData()
            assertEquals(
                weatherViewModel.weatherState.value?.errorMessage,
                ErrorMessageEnum.ErrorWhileFetching.value
            )
        }
}