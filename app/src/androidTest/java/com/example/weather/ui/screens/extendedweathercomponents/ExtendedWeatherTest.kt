package com.example.weather.ui.screens.extendedweathercomponents

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.weather.ui.model.DescriptionExtendedUi
import com.example.weather.ui.model.MainExtendedUi
import com.example.weather.ui.model.WeatherExtendedDataUi
import com.example.weather.ui.model.WindExtendedUi
import org.junit.Rule
import org.junit.Test

class ExtendedWeatherTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun onClickCausesExpandAndShowsRequiredFields() {
        composeTestRule.setContent {
            WeatherItem(weatherExtendedDataUi = testWeatherExtendedDataUi)
        }
        composeTestRule.onNodeWithContentDescription("weatherItem").assertHasClickAction()
        composeTestRule.onNodeWithContentDescription("weatherItem").performClick()
        composeTestRule.onNodeWithContentDescription("expandableColumn").assertIsDisplayed()

        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.dt).assertExists()
        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.weather[0].description)
            .assertExists()
        composeTestRule.onNodeWithContentDescription("weatherIcon").assertExists()
        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.main.temp).assertExists()
        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.main.pressure).assertExists()
        composeTestRule.onNodeWithContentDescription("pressureIcon").assertExists()
        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.main.humidity).assertExists()
        composeTestRule.onNodeWithContentDescription("humidityIcon").assertExists()
        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.wind.speed).assertExists()
        composeTestRule.onNodeWithContentDescription("windIcon").assertExists()
    }
}

val testWeatherExtendedDataUi = WeatherExtendedDataUi(
    "EEE, d MMM HH:mm a",
    MainExtendedUi("10 °C", "1000 mBar", "25%"),
    "2023-01-24 15:00:00",
    listOf(DescriptionExtendedUi("clouds", "")),
    WindExtendedUi("15 km/h")
)