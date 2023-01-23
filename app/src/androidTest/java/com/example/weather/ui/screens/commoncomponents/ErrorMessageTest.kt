package com.example.weather.ui.screens.commoncomponents

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ErrorMessageTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val errorTextContentDescr = "errorText"
    private val refreshIconContentDescr = "refreshIconButton"

    @Test
    fun ifErrorMessageIsNullShouldNotShowTextAndIcon() {
        val errorMessageStr: String? = null
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = {}, errorMessage = errorMessageStr)
        }
        composeTestRule.onNodeWithContentDescription(errorTextContentDescr).assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription(refreshIconContentDescr).assertDoesNotExist()
    }

    @Test
    fun ifErrorMessageIsEmptyShouldNotShowTextAndIcon() {
        val errorMessageStr = ""
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = {}, errorMessage = errorMessageStr)
        }
        composeTestRule.onNodeWithContentDescription(errorTextContentDescr).assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription(refreshIconContentDescr).assertDoesNotExist()
    }

    @Test
    fun passedErrorMessageShouldBeDisplayed() {
        val errorMessageStr = "error"
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = {}, errorMessage = errorMessageStr)
        }
        composeTestRule
            .onNodeWithContentDescription(errorTextContentDescr)
            .assertTextEquals(errorMessageStr)
    }

    @Test
    fun passedFunctionShouldBeCalledInOnClickAction() {
        var wasCalled = false
        composeTestRule.setContent {
            ErrorMessage(fetchWeatherData = { wasCalled = true }, errorMessage = "errorString")
        }
        composeTestRule.onNodeWithContentDescription(refreshIconContentDescr).assertExists()
        composeTestRule.onNodeWithContentDescription(refreshIconContentDescr).assertHasClickAction()
        composeTestRule.onNodeWithContentDescription(refreshIconContentDescr).performClick()
        assert(wasCalled)
    }
}