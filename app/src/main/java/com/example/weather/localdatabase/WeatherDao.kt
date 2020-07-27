package com.example.weather.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.localdatabase.model.CurrentWeatherEntity
import com.example.weather.localdatabase.model.ExtendedWeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExtendedWeather(extendedWeatherEntity: ExtendedWeatherEntity)

    @Query(value = "Select * from current_weather")
    fun getCurrentWeather(): LiveData<CurrentWeatherEntity>

    @Query(value = "Select * from extended_weather")
    fun getExtendedWeather(): LiveData<ExtendedWeatherEntity>
}