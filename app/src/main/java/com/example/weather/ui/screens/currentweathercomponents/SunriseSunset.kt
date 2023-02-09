package com.example.weather.ui.screens.currentweathercomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.weather.R

@Composable
fun SunriseSunset(sunrise: String, sunset: String) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.sunrise_sunset_animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        speed = 7f
    )
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(
                    topStartPercent = 75,
                    topEndPercent = 75,
                    bottomStartPercent = 10,
                    bottomEndPercent = 10
                )
            )
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            LottieAnimation(
                modifier = Modifier
                    .size(height = 200.dp, width = 500.dp)
                    .semantics { contentDescription = "sunriseSunsetAnimation" },
                composition = composition,
                progress = { progress },
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Sunrise", color = Color.White)
                    Text(text = sunrise, color = Color.White)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Sunset", color = Color.White)
                    Text(text = sunset, color = Color.White)
                }
            }
        }
    }
}