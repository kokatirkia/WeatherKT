package com.example.weather.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weather.data.repository.WeatherFactory
import com.example.weather.domain.usecases.FetchWeatherFromApiUseCase
import com.example.weather.domain.usecases.FetchWeatherFromLocalSourceUseCase
import com.example.weather.ui.model.WeatherState
import com.example.weather.ui.model.mapper.toWeatherUi
import com.example.weather.ui.screens.WeatherTabScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
        whenever(fetchWeatherFromLocalSourceUseCase.invoke())
            .thenReturn(flow { emit(WeatherFactory.makeWeather()) })

        weatherViewModel = WeatherViewModel(
            fetchWeatherFromApiUseCase,
            fetchWeatherFromLocalSourceUseCase
        )

        weatherViewModel.weatherState.observeForever(weatherStateObserver)
        weatherViewModel.cityNameTextFieldValue.observeForever(cityNameTextFieldValueObserver)
        weatherViewModel.selectedTabScreen.observeForever(selectedTabScreenObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun cityNameTextFieldValue_InitialValueShouldBeEmptyString() = coroutineRule.runBlockingTest {
        val textFieldValue = weatherViewModel.cityNameTextFieldValue.value

        assert(textFieldValue == "")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onTextFieldValueChanged_shouldChangeValue() = coroutineRule.runBlockingTest {
        val newTextValue = "newCity"
        weatherViewModel.onTextFieldValueChanged(newTextValue)

        verify(cityNameTextFieldValueObserver).onChanged(eq(newTextValue))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun selectedTabScreen_InitialValueShouldBeCurrent() = coroutineRule.runBlockingTest {
        val tabScreen = weatherViewModel.selectedTabScreen.value

        assert(tabScreen == WeatherTabScreen.Current)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun onSelectedTabScreenChanged_shouldChangeTabScreen() = coroutineRule.runBlockingTest {
        val weatherTabScreen = WeatherTabScreen.FiveDays
        weatherViewModel.onSelectedTabScreenChanged(weatherTabScreen)

        verify(selectedTabScreenObserver).onChanged(eq(weatherTabScreen))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchWeatherData_shouldChangeWeatherState() = coroutineRule.runBlockingTest {
        coroutineRule.pauseDispatcher()

        val weather = WeatherFactory.makeWeather()
        val weatherFlow = flow { emit(weather) }
        whenever(fetchWeatherFromLocalSourceUseCase.invoke()).thenReturn(weatherFlow)

        weatherViewModel.fetchWeatherData()

        assert(weatherViewModel.weatherState.value?.loading ?: false)

        coroutineRule.resumeDispatcher()

        val weatherState = WeatherState(
            weatherUi = weather.toWeatherUi(),
            errorMessage = null,
            loading = false
        )

        verify(weatherStateObserver).onChanged(eq(weatherState))
    }
}