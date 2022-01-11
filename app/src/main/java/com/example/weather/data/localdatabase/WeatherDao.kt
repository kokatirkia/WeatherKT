package com.example.weather.data.localdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.localdatabase.model.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherEntity)

    @Query(value = "Select * from weather")
    suspend fun getWeather(): WeatherEntity?

    @Query(value = "Select count(*) from weather")
    suspend fun getWeatherCount(): Int
}