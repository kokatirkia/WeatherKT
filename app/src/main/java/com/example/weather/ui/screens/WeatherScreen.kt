package com.example.weather.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.ui.WeatherViewModel
import com.example.weather.ui.components.*
import com.example.weather.ui.model.WeatherState

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState: WeatherState? by weatherViewModel.weatherState.observeAsState()
    val selectedTabIndex by weatherViewModel.selectedTabIndex.observeAsState(0)

    WeatherScreenComponent(
        weatherState = weatherState,
        selectedTabIndex = selectedTabIndex,
        onSelectedIndexChanged = { weatherViewModel.onSelectedIndexChanged(it) },
        fetchWeatherData = { weatherViewModel.fetchWeatherData() }
    )
}

@Composable
fun WeatherScreenComponent(
    weatherState: WeatherState?,
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

        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.White
        ) {
            for (i in 0..1) {
                Tab(
                    selected = selectedTabIndex == i,
                    onClick = { onSelectedIndexChanged(i) },
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                ) {
                    if (i == 0) Text(text = "Current")
                    else Text(text = "5 days")
                }
            }
        }

        weatherState?.let { weatherState ->
            if (weatherState.noInternetConnection) {
                NoInternetConnection(fetchWeatherData = fetchWeatherData)
            }
            if (weatherState.errorWhileFetching) {
                ErrorFetchingWeather(
                    weatherState.responseMessage,
                    fetchWeatherData = fetchWeatherData
                )
                return@let
            }
            if (selectedTabIndex == 0) {
                weatherState.weatherUi?.let { CurrentWeather(it.currentWeatherUi) }
            } else {
                weatherState.weatherUi?.let { ExtendedWeather(it.extendedWeather) }
            }
        } ?: CircularProgressBar()
    }
}