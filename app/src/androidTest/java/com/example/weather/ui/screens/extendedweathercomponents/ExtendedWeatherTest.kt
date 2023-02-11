package com.example.weather.ui.screens.extendedweathercomponents

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.weather.ui.model.*
import org.junit.Rule
import org.junit.Test

class ExtendedWeatherTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun onClickCausesExpandAndShowsRequiredFields() {
        composeTestRule.setContent {
            WeatherItem(weatherItem = testWeatherItem)
        }
        composeTestRule.onNodeWithContentDescription("weatherItem").assertHasClickAction()
        composeTestRule.onNodeWithContentDescription("weatherItem").performClick()
        composeTestRule.onNodeWithContentDescription("expandableColumn").assertIsDisplayed()

        composeTestRule.onNodeWithText(testWeatherItem.dt).assertExists()
        composeTestRule.onNodeWithText(testWeatherItem.weather[0].description)
            .assertExists()
        composeTestRule.onNodeWithContentDescription("weatherIcon").assertExists()
        composeTestRule.onNodeWithText(testWeatherItem.main.temp).assertExists()
        composeTestRule.onNodeWithText(testWeatherItem.main.pressure).assertExists()
        composeTestRule.onNodeWithContentDescription("pressureIcon").assertExists()
        composeTestRule.onNodeWithText(testWeatherItem.main.humidity).assertExists()
        composeTestRule.onNodeWithContentDescription("humidityIcon").assertExists()
        composeTestRule.onNodeWithText(testWeatherItem.wind.speed).assertExists()
        composeTestRule.onNodeWithContentDescription("windIcon").assertExists()
    }
}

val testWeatherItem = ExtendedWeatherUi.WeatherItem(
    "EEE, d MMM HH:mm a",
    ExtendedWeatherUi.Main("10 °C", "1000 mBar", "25%"),
    "2023-01-24 15:00:00",
    listOf(ExtendedWeatherUi.Weather("clouds", "")),
    ExtendedWeatherUi.Wind("15 km/h")
)