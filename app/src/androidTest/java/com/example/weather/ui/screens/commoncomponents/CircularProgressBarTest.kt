package com.example.weather.ui.screens.commoncomponents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class CircularProgressBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val circularProgressContentDescr = "circularProgressIndicator"

    @Test
    fun circularProgressIndicatorExists() {
        composeTestRule.setContent {
            CircularProgressBar()
        }
        composeTestRule.onNodeWithContentDescription(circularProgressContentDescr).assertExists()
    }
}