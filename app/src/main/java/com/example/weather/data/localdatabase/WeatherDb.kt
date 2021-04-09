package com.example.weather.data.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.data.localdatabase.model.WeatherEntity
import com.example.weather.data.localdatabase.typeconverter.CurrentWeatherConverter
import com.example.weather.data.localdatabase.typeconverter.ExtendedWeatherConverter

@Database(
    entities = [(WeatherEntity::class)],
    version = 8,
    exportSchema = false
)
@TypeConverters(CurrentWeatherConverter::class, ExtendedWeatherConverter::class)
abstract class WeatherDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}