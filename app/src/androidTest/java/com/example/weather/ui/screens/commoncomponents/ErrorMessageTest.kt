package com.example.weather.ui.screens.commoncomponents

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ErrorMessageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ifErrorMessageIsNullShouldNotShowTextAndIcon() {
        val errorMessageStr: String? = null
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = {}, errorMessage = errorMessageStr)
        }
        composeTestRule.onNodeWithTag("errorTestTag").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("refreshIconButton").assertDoesNotExist()
    }

    @Test
    fun ifErrorMessageIsEmptyShouldNotShowTextAndIcon() {
        val errorMessageStr = ""
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = {}, errorMessage = errorMessageStr)
        }
        composeTestRule.onNodeWithTag("errorTestTag").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("refreshIconButton").assertDoesNotExist()
    }

    @Test
    fun passedErrorMessageShouldBeDisplayed() {
        val errorMessageStr = "error"
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = {}, errorMessage = errorMessageStr)
        }
        composeTestRule.onNodeWithTag("errorTestTag").assertTextEquals(errorMessageStr)
    }

    @Test
    fun passedFunctionShouldBeCalledInOnClickAction() {
        var wasCalled = false
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = { wasCalled = true }, errorMessage = "errorString")
        }
        composeTestRule.onNodeWithContentDescription("refreshIconButton").assertExists()
        composeTestRule.onNodeWithContentDescription("refreshIconButton").assertHasClickAction()
        composeTestRule.onNodeWithContentDescription("refreshIconButton").performClick()
        assert(wasCalled)
    }
}