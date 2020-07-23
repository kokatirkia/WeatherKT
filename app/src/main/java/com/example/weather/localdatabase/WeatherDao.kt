package com.example.weather.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.localdatabase.model.CurrentWeather
import com.example.weather.localdatabase.model.ExtendedWeather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeather: CurrentWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExtendedWeather(extendedWeather: ExtendedWeather)

    @Query(value = "Select * from current_weather")
    fun getCurrentWeather(): LiveData<CurrentWeather>

    @Query(value = "Select * from extended_weather")
    fun getExtendedWeather(): LiveData<ExtendedWeather>
}