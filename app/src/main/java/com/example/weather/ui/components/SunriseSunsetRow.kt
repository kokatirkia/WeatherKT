package com.example.weather.ui.components

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
fun SunriseSunsetRow(sunrise: String, sunset: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.sunrise),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(text = "Sunrise", color = Color.White)
            Text(
                text = sunrise,
                color = Color.White
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.sunset),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(text = "Sunset", color = Color.White)
            Text(text = sunset, color = Color.White)
        }
    }
}
