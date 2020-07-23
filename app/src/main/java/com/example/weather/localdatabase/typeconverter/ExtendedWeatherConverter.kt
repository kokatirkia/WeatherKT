package com.example.weather.localdatabase.typeconverter

import androidx.room.TypeConverter
import com.example.weather.networking.model.ExtendedWeatherData
import com.google.gson.Gson

class ExtendedWeatherConverter {
    val gson = Gson()

    @TypeConverter
    fun fromExtendedWeatherToJson(value: ExtendedWeatherData): String {
        return gson.toJson(value, ExtendedWeatherData::class.java)
    }

    @TypeConverter
    fun jsonToCurrentWeather(value: String): ExtendedWeatherData {
        return gson.fromJson(value, ExtendedWeatherData::class.java)
    }
}