package com.example.weather.ui.screens.commoncomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox(
    cityName: String,
    onCityNameChange: (String) -> Unit,
    fetchWeatherData: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        value = cityName,
        onValueChange = onCityNameChange,
        trailingIcon = {
            IconButton(onClick = {
                fetchWeatherData()
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
            fetchWeatherData()
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
    )
}