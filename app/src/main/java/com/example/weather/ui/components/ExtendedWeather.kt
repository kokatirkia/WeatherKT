package com.example.weather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.weather.ui.model.ExtendedWeatherUi
import com.example.weather.ui.model.WeatherExtendedDataUi
import com.example.weather.utils.Constants

@Composable
fun ExtendedWeather(extendedWeatherState: ExtendedWeatherUi) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        extendedWeatherState.list.forEach {
            item { WeatherItem(weatherExtendedDataUi = it) }
        }
    }
}

@Composable
fun WeatherItem(weatherExtendedDataUi: WeatherExtendedDataUi) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .size(70.dp)
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(10.dp),
    ) {
        Text(
            text = weatherExtendedDataUi.dt,
            color = Color.White,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            text = weatherExtendedDataUi.weather[0].description,
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
        )
        Image(
            painter = rememberImagePainter(Constants.iconUrl + weatherExtendedDataUi.weather[0].icon),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(35.dp)
                .align(Alignment.TopEnd)
        )
        Text(
            text = weatherExtendedDataUi.main.temp,
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}