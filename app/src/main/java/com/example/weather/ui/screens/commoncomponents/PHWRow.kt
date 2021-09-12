package com.example.weather.ui.screens.commoncomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.R

@Composable
fun PHWRow(pressure: String, humidity: String, windSpeed: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.pressure),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(text = "Pressure", color = Color.White)
            Text(
                text = pressure,
                color = Color.White
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.humidity),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(text = "Humidity", color = Color.White)
            Text(
                text = humidity,
                color = Color.White
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.wind),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(text = "Wind", color = Color.White)
            Text(text = windSpeed, color = Color.White)
        }
    }
}