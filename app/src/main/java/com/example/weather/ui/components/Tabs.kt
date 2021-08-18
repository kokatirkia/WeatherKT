package com.example.weather.ui.components

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.ui.screens.WeatherTabScreen

@Composable
fun Tabs(
    weatherTabScreens: List<WeatherTabScreen>,
    selectedTabScreen: WeatherTabScreen,
    onSelectedTabScreenChanged: (WeatherTabScreen) -> Unit,
    modifier: Modifier
) {
    TabRow(
        selectedTabIndex = selectedTabScreen.ordinal,
        contentColor = Color.White
    ) {
        weatherTabScreens.forEach {
            Tab(
                selected = selectedTabScreen == it,
                onClick = { onSelectedTabScreenChanged(it) },
                modifier = modifier
            ) {
                when (it) {
                    WeatherTabScreen.Current -> Text(text = stringResource(R.string.current))
                    WeatherTabScreen.FiveDays -> Text(text = stringResource(R.string.five_days))
                }
            }
        }
    }
}