package com.example.weather.data.localdatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDatabase: WeatherDb
    private lateinit var weatherDao: WeatherDao

    @Before
    fun initDatabase() {
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDb::class.java
        ).allowMainThreadQueries().build()

        weatherDao = weatherDatabase.weatherDao()
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertWeatherAndGetBack() = runBlockingTest {
        val weatherEntity = WeatherEntityFactory.makeWeatherEntity()
        weatherDao.insertWeather(weatherEntity)
        val weatherFromDb = weatherDao.getWeather()
        assertEquals(weatherFromDb, weatherEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertWeatherReplacesOldWeatherOnPrimaryKeyConflict() = runBlockingTest {
        val weatherEntityFirst = WeatherEntityFactory.makeWeatherEntity()
        val weatherEntitySecond = WeatherEntityFactory.makeWeatherEntity()
        weatherDao.insertWeather(weatherEntityFirst)
        weatherDao.insertWeather(weatherEntitySecond)

        val weatherCount = weatherDao.getWeatherCount()
        assertEquals(weatherCount, 1)

        val weatherFromDb = weatherDao.getWeather()
        assertEquals(weatherFromDb, weatherEntitySecond)
    }
}