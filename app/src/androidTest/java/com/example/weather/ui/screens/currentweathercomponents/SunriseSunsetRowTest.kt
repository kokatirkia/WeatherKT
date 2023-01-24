package com.example.weather.ui.screens.currentweathercomponents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SunriseSunsetRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val sunrise = "7:00"
    private val sunset = "9:00"

    @Before
    fun setup(){
        composeTestRule.setContent {
            SunriseSunsetRow(sunrise = sunrise, sunset = sunset)
        }
    }

    @Test
    fun passedStringsAreShown(){
        composeTestRule.onNodeWithText(sunrise).assertExists()
        composeTestRule.onNodeWithText(sunset).assertExists()
    }

    @Test
    fun sunriseIconExists(){
        composeTestRule.onNodeWithContentDescription("sunriseIcon").assertExists()
    }

    @Test
    fun sunsetIconExists(){
        composeTestRule.onNodeWithContentDescription("sunsetIcon").assertExists()
    }
}