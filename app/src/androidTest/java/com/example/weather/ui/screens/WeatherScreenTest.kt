package com.example.weather.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.weather.ui.model.*
import org.junit.Rule
import org.junit.Test

class WeatherScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun circularProgressBarIsShownWhenLoadingIsTrue() {
        composeTestRule.setContent {
            WeatherScreen(
                weatherState = WeatherState(loading = true),
                selectedTabScreen = WeatherTabScreen.Current,
                onSelectedTabScreenChanged = {},
                fetchWeatherData = {},
                cityName = "Tbilisi",
                onCityNameChange = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("circularProgressIndicator").assertExists()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun errorMessageIsShownWhenErrorMessageIsProvided() {
        val errorText = "someErrorText"
        composeTestRule.setContent {
            WeatherScreen(
                weatherState = WeatherState(errorMessage = errorText, loading = false),
                selectedTabScreen = WeatherTabScreen.Current,
                onSelectedTabScreenChanged = {},
                fetchWeatherData = {},
                cityName = "Tbilisi",
                onCityNameChange = {}
            )
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun currentWeatherScreenIsShownWhenCurrentTabIsSelected() {
        composeTestRule.setContent {
            WeatherScreen(
                weatherState = WeatherState(
                    weatherUi = WeatherUi(
                        testCurrentWeatherUi,
                        testExtendedWeatherUi
                    ),
                    loading = false
                ),
                selectedTabScreen = WeatherTabScreen.Current,
                onSelectedTabScreenChanged = {},
                fetchWeatherData = {},
                cityName = "Tbilisi",
                onCityNameChange = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("currentWeatherColumn").assertExists()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun extendedWeatherScreenIsShownWhenFiveDaysTabIsSelected() {
        composeTestRule.setContent {
            WeatherScreen(
                weatherState = WeatherState(
                    weatherUi = WeatherUi(
                        testCurrentWeatherUi,
                        testExtendedWeatherUi
                    ),
                    loading = false
                ),
                selectedTabScreen = WeatherTabScreen.FiveDays,
                onSelectedTabScreenChanged = {},
                fetchWeatherData = {},
                cityName = "Tbilisi",
                onCityNameChange = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("extendedWeatherList").assertExists()
    }
}

val testCurrentWeatherUi = CurrentWeatherUi(
    listOf(CurrentWeatherUi.Weather("clouds", "scattered clouds", "")),
    CurrentWeatherUi.Main(
        "10 °C",
        "11 °C",
        "1000 mBar",
        "25 %"
    ),
    CurrentWeatherUi.Wind("15 km/h"),
    CurrentWeatherUi.Sys("6:00", "7:00"),
    "EEEE",
    "hh:mm a",
    "London"
)

val testExtendedWeatherUi = ExtendedWeatherUi(
    listOf(
        ExtendedWeatherUi.WeatherItem(
            "EEE, d MMM HH:mm a",
            ExtendedWeatherUi.Main("10 °C", "1000 mBar", "25%"),
            "2023-01-24 15:00:00",
            listOf(ExtendedWeatherUi.Weather("clouds", "")),
            ExtendedWeatherUi.Wind("15 km/h")
        )
    )
)