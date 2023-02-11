package com.example.weather.ui.screens.extendedweathercomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.weather.ui.model.ExtendedWeatherUi
import com.example.weather.utils.Constants

@Composable
fun WeatherItemMainBox(weatherItem: ExtendedWeatherUi.WeatherItem) {
    Box(
        modifier = Modifier
            .semantics { contentDescription = "weatherItemMainBox" }
            .fillMaxSize()
            .size(75.dp)
            .padding(10.dp)
    ) {
        Text(
            text = weatherItem.dt,
            color = Color.White,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            text = weatherItem.weather[0].description,
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
        )
        Image(
            painter = rememberImagePainter(
                data = Constants.iconUrl + weatherItem.weather[0].icon,
                builder = { crossfade(true) }
            ),
            contentDescription = "weatherIcon",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(35.dp)
                .align(Alignment.TopEnd)
        )
        Text(
            text = weatherItem.main.temp,
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
