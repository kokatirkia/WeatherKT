package com.example.weather.ui.screens.extendedweathercomponents

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
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
    fun onClickCausesExpand() {
        composeTestRule.setContent {
            WeatherItem(weatherExtendedDataUi = testWeatherExtendedDataUi)
        }
        composeTestRule.onNodeWithContentDescription("weatherItem").assertHasClickAction()
        composeTestRule.onNodeWithContentDescription("weatherItem").performClick()
        composeTestRule.onNodeWithContentDescription("expandableColumn").assertIsDisplayed()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun weatherItemMainBoxExists() {
        composeTestRule.setContent {
            WeatherItem(weatherExtendedDataUi = testWeatherExtendedDataUi)
        }
        composeTestRule.onNodeWithContentDescription("weatherItemMainBox").assertExists()
    }
}

val testWeatherExtendedDataUi = WeatherExtendedDataUi(
    "EEE, d MMM HH:mm a",
    MainExtendedUi("10 °C", "1000 mBar", "25%"),
    "2023-01-24 15:00:00",
    listOf(DescriptionExtendedUi("clouds", "")),
    WindExtendedUi("15 km/h")
)