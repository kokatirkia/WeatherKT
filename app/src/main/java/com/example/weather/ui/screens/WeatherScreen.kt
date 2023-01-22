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
import com.example.weather.ui.model.WeatherState
import com.example.weather.ui.screens.commoncomponents.CircularProgressBar
import com.example.weather.ui.screens.commoncomponents.ErrorMessage
import com.example.weather.ui.screens.commoncomponents.SearchBox
import com.example.weather.ui.screens.commoncomponents.Tabs
import com.example.weather.ui.screens.currentweathercomponents.CurrentWeather
import com.example.weather.ui.screens.extendedweathercomponents.ExtendedWeather

enum class WeatherTabScreen(val title: String) {
    Current("Current"), FiveDays("5 days")
}

@ExperimentalAnimationApi
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState: WeatherState by weatherViewModel.weatherState.observeAsState(WeatherState())
    val selectedTabScreen by weatherViewModel.selectedTabScreen.observeAsState(WeatherTabScreen.Current)
    val cityName: String by weatherViewModel.cityNameTextFieldValue.observeAsState("")

    WeatherScreen(
        weatherState = weatherState,
        selectedTabScreen = selectedTabScreen,
        onSelectedTabScreenChanged = { weatherViewModel.onSelectedTabScreenChanged(it) },
        fetchWeatherData = { weatherViewModel.fetchWeatherData() },
        cityName = cityName,
        onCityNameChange = { weatherViewModel.onTextFieldValueChanged(it) }
    )
}

@ExperimentalAnimationApi
@Composable
fun WeatherScreen(
    weatherState: WeatherState,
    selectedTabScreen: WeatherTabScreen,
    onSelectedTabScreenChanged: (WeatherTabScreen) -> Unit,
    fetchWeatherData: () -> Unit,
    cityName: String,
    onCityNameChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        SearchBox(
            cityName = cityName,
            onCityNameChange = onCityNameChange,
            fetchWeatherData = fetchWeatherData
        )

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
            ErrorMessage(fetchWeatherData, weatherState.errorMessage)
            WeatherData(weatherState, selectedTabScreen)
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
                slideInHorizontally() + fadeIn() with slideOutHorizontally() + fadeOut()
            } else {
                slideInHorizontally() + fadeIn() with slideOutHorizontally() + fadeOut()
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