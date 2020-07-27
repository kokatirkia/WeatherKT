package com.example.weather.localdatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weather.localdatabase.typeconverter.ExtendedWeatherConverter
import com.example.weather.networking.model.ExtendedWeatherData

@Entity(tableName = "extended_weather")
data class ExtendedWeatherEntity(
    @PrimaryKey
    var id: Int,
    @TypeConverters(ExtendedWeatherConverter::class)
    var extendedWeatherData: ExtendedWeatherData
)