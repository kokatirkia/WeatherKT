package com.example.weather.ui.screens.extendedweathercomponents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class WeatherItemMainBoxTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun requiredFieldsExist() {
        composeTestRule.setContent {
            WeatherItemMainBox(weatherExtendedDataUi = testWeatherExtendedDataUi)
        }
        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.dt).assertExists()
        composeTestRule
            .onNodeWithText(testWeatherExtendedDataUi.weather[0].description)
            .assertExists()
        composeTestRule.onNodeWithContentDescription("weatherIcon").assertExists()
        composeTestRule.onNodeWithText(testWeatherExtendedDataUi.main.temp).assertExists()
    }
}