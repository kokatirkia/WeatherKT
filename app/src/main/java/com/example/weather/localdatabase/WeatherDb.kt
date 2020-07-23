package com.example.weather.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.localdatabase.model.CurrentWeather
import com.example.weather.localdatabase.model.ExtendedWeather
import com.example.weather.localdatabase.typeconverter.CurrentWeatherConverter
import com.example.weather.localdatabase.typeconverter.ExtendedWeatherConverter

@Database(
    entities = [(CurrentWeather::class), (ExtendedWeather::class)],
    version = 6,
    exportSchema = false
)
@TypeConverters(CurrentWeatherConverter::class,ExtendedWeatherConverter::class)
abstract class WeatherDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}