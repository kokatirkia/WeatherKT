package com.example.weather.ui.screens.commoncomponents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PHWRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passedStringsAreShown() {
        val pressure = "1000 mBar"
        val humidity = "25%"
        val windSpeed = "15 km/h"
        composeTestRule.setContent {
            PHWRow(pressure = pressure, humidity = humidity, windSpeed = windSpeed)
        }
        composeTestRule.onNodeWithText(pressure).assertExists()
        composeTestRule.onNodeWithText(humidity).assertExists()
        composeTestRule.onNodeWithText(windSpeed).assertExists()
    }
}