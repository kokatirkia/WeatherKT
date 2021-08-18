package com.example.weather.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.ui.WeatherViewModel
import com.example.weather.ui.components.*
import com.example.weather.ui.model.WeatherState
import com.example.weather.utils.ResponseMessageEnum

enum class WeatherTabScreen {
    Current, FiveDays
}

@ExperimentalAnimationApi
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState: WeatherState by weatherViewModel.weatherState.observeAsState(WeatherState())
    val selectedTabScreen by weatherViewModel.selectedTabScreen.observeAsState(WeatherTabScreen.Current)

    WeatherScreenComponent(
        weatherState = weatherState,
        selectedTabScreen = selectedTabScreen,
        onSelectedTabScreenChanged = { weatherViewModel.onSelectedTabScreenChanged(it) },
        fetchWeatherData = { weatherViewModel.fetchWeatherData() }
    )
}

@ExperimentalAnimationApi
@Composable
fun WeatherScreenComponent(
    weatherState: WeatherState,
    selectedTabScreen: WeatherTabScreen,
    onSelectedTabScreenChanged: (WeatherTabScreen) -> Unit,
    fetchWeatherData: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        SearchBox()

        Tabs(
            WeatherTabScreen.values().toList(),
            selectedTabScreen,
            onSelectedTabScreenChanged,
            Modifier
                .heightIn(min = 48.dp)
                .padding(horizontal = 16.dp, vertical = 2.dp)
        )

        if (weatherState.loading) {
            CircularProgressBar()
        } else {
            if (weatherState.responseMessage == ResponseMessageEnum.NoInternetConnection) {
                NoInternetConnection(fetchWeatherData = fetchWeatherData)
            }
            when (weatherState.responseMessage) {
                ResponseMessageEnum.ErrorWhileFetching, ResponseMessageEnum.CityNotFound -> {
                    ErrorFetchingWeather(weatherState.responseMessage!!.value, fetchWeatherData)
                }
                else -> WeatherData(weatherState, selectedTabScreen)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WeatherData(weatherState: WeatherState, selectedTabScreen: WeatherTabScreen) {
    AnimatedContent(
        targetState = selectedTabScreen,
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
    ) { tabScreen ->
        when (tabScreen) {
            WeatherTabScreen.Current -> weatherState.weatherUi?.let { CurrentWeather(it.currentWeatherUi) }
            WeatherTabScreen.FiveDays -> weatherState.weatherUi?.let { ExtendedWeather(it.extendedWeatherUi) }
        }
    }
}