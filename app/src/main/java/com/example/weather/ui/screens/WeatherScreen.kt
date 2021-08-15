package com.example.weather.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.ui.WeatherViewModel
import com.example.weather.ui.components.*
import com.example.weather.ui.model.WeatherState
import com.example.weather.utils.ResponseMessageEnum

@ExperimentalAnimationApi
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState: WeatherState by weatherViewModel.weatherState.observeAsState(WeatherState())
    val selectedTabIndex by weatherViewModel.selectedTabIndex.observeAsState(0)

    WeatherScreenComponent(
        weatherState = weatherState,
        selectedTabIndex = selectedTabIndex,
        onSelectedIndexChanged = { weatherViewModel.onSelectedIndexChanged(it) },
        fetchWeatherData = { weatherViewModel.fetchWeatherData() }
    )
}

@ExperimentalAnimationApi
@Composable
fun WeatherScreenComponent(
    weatherState: WeatherState,
    selectedTabIndex: Int,
    onSelectedIndexChanged: (Int) -> Unit,
    fetchWeatherData: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        SearchBox()

        Tabs(selectedTabIndex, onSelectedIndexChanged)

        if (weatherState.loading) {
            CircularProgressBar()
        } else {
            if (weatherState.responseMessage == ResponseMessageEnum.NoInternetConnection) {
                NoInternetConnection(fetchWeatherData = fetchWeatherData)
            }
            when (weatherState.responseMessage) {
                ResponseMessageEnum.ErrorWhileFetching -> {
                    ErrorFetchingWeather(weatherState.responseMessage!!.value, fetchWeatherData)
                }
                ResponseMessageEnum.CityNotFound -> {
                    ErrorFetchingWeather(weatherState.responseMessage!!.value, fetchWeatherData)
                }
                else -> WeatherData(weatherState, selectedTabIndex)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WeatherData(weatherState: WeatherState, selectedTabIndex: Int) {
    AnimatedContent(
        targetState = selectedTabIndex,
        transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally({ width -> width }) + fadeIn() with
                        slideOutHorizontally({ width -> -width }) + fadeOut()
            } else {
                slideInHorizontally({ width -> -width }) + fadeIn() with
                        slideOutHorizontally({ width -> width }) + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { tabIndex ->
        when (tabIndex) {
            0 -> weatherState.weatherUi?.let { CurrentWeather(it.currentWeatherUi) }
            1 -> weatherState.weatherUi?.let { ExtendedWeather(it.extendedWeatherUi) }
        }
    }
}