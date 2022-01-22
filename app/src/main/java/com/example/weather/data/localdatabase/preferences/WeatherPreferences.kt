package com.example.weather.data.localdatabase.preferences

interface WeatherPreferences {
    fun saveCityName(cityName: String?)
    fun getCityName(): String
}