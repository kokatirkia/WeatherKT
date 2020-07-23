package com.example.weather.localdatabase.typeconverter

import androidx.room.TypeConverter
import com.example.weather.networking.model.CurrentWeatherData
import com.google.gson.Gson

class CurrentWeatherConverter {
    val gson = Gson()

    @TypeConverter
    fun fromCurrentWeatherToJson(value: CurrentWeatherData): String {
        return gson.toJson(value, CurrentWeatherData::class.java)
    }

    @TypeConverter
    fun jsonToCurrentWeather(value: String): CurrentWeatherData {
        return gson.fromJson(value, CurrentWeatherData::class.java)
    }
}