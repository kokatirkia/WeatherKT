package com.example.weather.data.localdatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weather.data.localdatabase.typeconverter.CurrentWeatherConverter
import com.example.weather.data.localdatabase.typeconverter.ExtendedWeatherConverter

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey
    var id: Int = 0,
    @TypeConverters(CurrentWeatherConverter::class)
    var currentWeather: CurrentWeatherEntity,
    @TypeConverters(ExtendedWeatherConverter::class)
    var extendedWeather: ExtendedWeatherEntity
)