package com.example.weather.localdatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weather.localdatabase.typeconverter.CurrentWeatherConverter
import com.example.weather.networking.model.CurrentWeatherData

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey
    var id: Int,
    @TypeConverters(CurrentWeatherConverter::class)
    var currentWeatherData: CurrentWeatherData
)