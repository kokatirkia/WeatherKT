package com.example.weather.data.localdatabase.typeconverter

import androidx.room.TypeConverter
import com.example.weather.data.localdatabase.model.ExtendedWeatherEntity
import com.google.gson.Gson

class ExtendedWeatherConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromExtendedWeatherToJson(value: ExtendedWeatherEntity): String {
        return gson.toJson(value, ExtendedWeatherEntity::class.java)
    }

    @TypeConverter
    fun jsonToCurrentWeather(value: String): ExtendedWeatherEntity {
        return gson.fromJson(value, ExtendedWeatherEntity::class.java)
    }
}