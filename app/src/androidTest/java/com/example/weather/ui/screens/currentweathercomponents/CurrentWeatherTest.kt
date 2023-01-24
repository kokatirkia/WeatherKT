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
        composeTestRule.onNodeWithText(testCurrentWeatherUi.sys.sunrise).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.sys.sunset).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.main.pressure).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.main.humidity).assertExists()
        composeTestRule.onNodeWithText(testCurrentWeatherUi.wind.speed).assertExists()
    }
}

val testCurrentWeatherUi = CurrentWeatherUi(
    listOf(WeatherDescriptionUi("clouds", "scattered clouds", "")),
    MainUi(
        "10 °C",
        "11 °C",
        "1000 mBar",
        "25 %"
    ),
    WindUi("15 km/h"),
    SysUi("6:00", "7:00"),
    "EEEE",
    "hh:mm a",
    "London"
)