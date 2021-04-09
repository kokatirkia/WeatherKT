package com.example.weather.data.localdatabase.typeconverter

import androidx.room.TypeConverter
import com.example.weather.data.localdatabase.model.CurrentWeatherEntity
import com.google.gson.Gson

class CurrentWeatherConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCurrentWeatherToJson(value: CurrentWeatherEntity): String {
        return gson.toJson(value, CurrentWeatherEntity::class.java)
    }

    @TypeConverter
    fun jsonToCurrentWeather(value: String): CurrentWeatherEntity {
        return gson.fromJson(value, CurrentWeatherEntity::class.java)
    }
}