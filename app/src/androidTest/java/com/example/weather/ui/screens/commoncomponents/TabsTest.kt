package com.example.weather.ui.screens.commoncomponents

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.weather.ui.screens.WeatherTabScreen
import org.junit.Rule
import org.junit.Test

class TabsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passedCurrentTabIsSelected() {
        composeTestRule.setContent {
            Tabs(
                weatherTabScreens = WeatherTabScreen.values().toList(),
                selectedTabScreen = WeatherTabScreen.Current,
                onSelectedTabScreenChanged = {},
                modifier = Modifier
            )
        }
        composeTestRule
            .onNodeWithText(WeatherTabScreen.Current.title)
            .assertIsSelected()
    }

    @Test
    fun passedFiveDaysTabIsSelected() {
        composeTestRule.setContent {
            Tabs(
                weatherTabScreens = WeatherTabScreen.values().toList(),
                selectedTabScreen = WeatherTabScreen.FiveDays,
                onSelectedTabScreenChanged = {},
                modifier = Modifier
            )
        }
        composeTestRule
            .onNodeWithText(WeatherTabScreen.FiveDays.title)
            .assertIsSelected()
    }

    @Test
    fun tabOnClickCallsPassedFunction() {
        var wasCalled = false
        composeTestRule.setContent {
            Tabs(
                weatherTabScreens = listOf(WeatherTabScreen.Current),
                selectedTabScreen = WeatherTabScreen.Current,
                onSelectedTabScreenChanged = { wasCalled = true },
                modifier = Modifier.testTag("tabTestTag")
            )
        }
        composeTestRule.onNodeWithTag("tabTestTag").assertExists()
        composeTestRule.onNodeWithTag("tabTestTag").assertHasClickAction()
        composeTestRule.onNodeWithTag("tabTestTag").performClick()
        assert(wasCalled)
    }
}