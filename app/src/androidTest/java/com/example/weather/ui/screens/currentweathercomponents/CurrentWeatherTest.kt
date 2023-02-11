package com.example.weather.ui.screens.currentweathercomponents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.weather.ui.model.*
import org.junit.Rule
import org.junit.Test

class CurrentWeatherTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun requiredFieldsAreShow() {
        composeTestRule.setContent {
            CurrentWeather(currentWeatherUi = testCurrentWeatherUi)
        }
        composeTestRule.onNodeWithText(testCurrentWeatherUi.name).assertExists()
        composeTestRule.onNodeWithContentDescription("weatherIcon").assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.day).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.time).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.main.temp).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.weather[0].description).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.main.feelsLike).assertExists()
        composeTestRule.onNodeWithContentDescription("sunriseSunsetAnimation").assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.sys.sunrise).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.sys.sunset).assertExists()
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