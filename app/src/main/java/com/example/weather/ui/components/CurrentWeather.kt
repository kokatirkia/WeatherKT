package com.example.weather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.weather.R
import com.example.weather.ui.model.CurrentWeatherUi
import com.example.weather.utils.Constants

@Composable
fun CurrentWeather(currentWeatherState: CurrentWeatherUi) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = currentWeatherState.name,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = currentWeatherState.main.temp,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = currentWeatherState.main.feelsLike,
            fontSize = 20.sp,
            color = Color.White
        )
        Text(
            text = currentWeatherState.weather[0].description,
            fontSize = 20.sp,
            color = Color.White
        )
        Image(
            painter = rememberImagePainter(
                Constants.iconUrl + currentWeatherState.weather[0].icon
            ),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        DetailBox(currentWeatherState)
    }
}

@Composable
fun DetailBox(currentWeatherState: CurrentWeatherUi) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(20.dp)
        ) {
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
                        text = currentWeatherState.sys.sunrise,
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
                    Text(text = currentWeatherState.sys.sunset, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 15.dp))
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
                        text = currentWeatherState.main.pressure,
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
                        text = currentWeatherState.main.humidity,
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
                    Text(text = currentWeatherState.wind.speed, color = Color.White)
                }
            }
        }
    }
}