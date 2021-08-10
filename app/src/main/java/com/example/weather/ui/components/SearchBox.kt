package com.example.weather.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.weather.ui.WeatherViewModel

@Composable
fun SearchBox(weatherViewModel: WeatherViewModel) {
    val textFieldValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        value = textFieldValue.value,
        onValueChange = {
            textFieldValue.value = it
        },
        trailingIcon = {
            IconButton(onClick = {
                weatherViewModel.fetchWeatherData(textFieldValue.value)
                focusManager.clearFocus()
            }) {
                Icon(Icons.Rounded.Search, "search", tint = Color.White)
            }
        },
        label = { Text(text = "Search", color = Color.White) },
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = MaterialTheme.colors.secondary,
            cursorColor = Color.White,
            disabledLabelColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardActions = KeyboardActions(onDone = {
            weatherViewModel.fetchWeatherData(textFieldValue.value)
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
    )
}