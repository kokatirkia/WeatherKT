package com.example.weather.ui.screens.commoncomponents

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchBoxTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val cityName = "Chicago"
    private val searchTextFieldContentDescription = "searchTextField"
    private val searchIconContentDescription = "searchIcon"

    @Test
    fun passedTextIsShown() {
        composeTestRule.setContent {
            SearchBox(cityName = cityName, onCityNameChange = {}, fetchWeatherData = {})
        }
        composeTestRule
            .onNodeWithContentDescription(searchTextFieldContentDescription, useUnmergedTree = true)
            .assertTextEquals(cityName)
    }

    @Test
    fun onClickCallsPassedFunction() {
        var fetchWeatherDataWasCalled = false
        composeTestRule.setContent {
            SearchBox(
                cityName = cityName,
                onCityNameChange = {},
                fetchWeatherData = { fetchWeatherDataWasCalled = true })
        }
        composeTestRule
            .onNodeWithContentDescription(searchIconContentDescription)
            .assertHasClickAction()
        composeTestRule
            .onNodeWithContentDescription(searchIconContentDescription)
            .performClick()
        assert(fetchWeatherDataWasCalled)
    }

    @Test
    fun onEnterClickCallsPassedFunction() {
        var fetchWeatherDataWasCalled = false
        composeTestRule.setContent {
            SearchBox(
                cityName = cityName,
                onCityNameChange = {},
                fetchWeatherData = { fetchWeatherDataWasCalled = true })
        }
        composeTestRule
            .onNodeWithContentDescription(searchTextFieldContentDescription)
            .performImeAction()
        assert(fetchWeatherDataWasCalled)
    }
}