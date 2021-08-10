package com.example.weather.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weather.ui.WeatherViewModel
import com.example.weather.ui.components.*
import com.example.weather.ui.model.WeatherState

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {

    val weatherState: WeatherState? by weatherViewModel.weatherState.observeAsState()

    val selectedIndex = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        SearchBox(weatherViewModel)

        TabRow(
            selectedTabIndex = selectedIndex.value,
            contentColor = Color.White
        ) {
            for (i in 0..1) {
                Tab(
                    selected = selectedIndex.value == i,
                    onClick = { selectedIndex.value = i },
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
                NoInternetConnection(weatherViewModel)
            }
            if (weatherState.errorWhileFetching) {
                ErrorFetchingWeather(weatherState.responseMessage, weatherViewModel)
                return@let
            }
            if (selectedIndex.value == 0) {
                weatherState.weatherUi?.let { CurrentWeather(it.currentWeatherUi) }
            } else {
                weatherState.weatherUi?.let { ExtendedWeather(it.extendedWeather) }
            }
        } ?: CircularProgressBar()
    }
}