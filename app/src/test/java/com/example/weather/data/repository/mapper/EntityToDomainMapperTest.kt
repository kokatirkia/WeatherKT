package com.example.weather.data.repository.mapper

import com.example.weather.data.repository.WeatherEntityFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class EntityToDomainMapperTest {
    @Test
    fun testEntityToDomainMapping() {
        val weatherEntity = WeatherEntityFactory.makeWeatherEntity()
        val weather = weatherEntity.toWeatherDomain()

        assertEquals(
            weatherEntity.currentWeather.weather[0].main,
            weather.currentWeather.weather[0].main
        )
        assertEquals(
            weatherEntity.currentWeather.weather[0].description,
            weather.currentWeather.weather[0].description
        )
        assertEquals(
            weatherEntity.currentWeather.weather[0].icon,
            weather.currentWeather.weather[0].icon
        )
        assertEquals(
            weatherEntity.currentWeather.main.temp,
            weather.currentWeather.main.temp,
            0.0
        )
        assertEquals(
            weatherEntity.currentWeather.main.feels_like,
            weather.currentWeather.main.feelsLike
        )
        assertEquals(
            weatherEntity.currentWeather.main.pressure,
            weather.currentWeather.main.pressure
        )
        assertEquals(
            weatherEntity.currentWeather.main.humidity,
            weather.currentWeather.main.humidity
        )
        assertEquals(
            weatherEntity.currentWeather.wind.speed,
            weather.currentWeather.wind.speed,
            0.0
        )
        assertEquals(
            weatherEntity.currentWeather.sys.sunrise,
            weather.currentWeather.sys.sunrise
        )
        assertEquals(
            weatherEntity.currentWeather.sys.sunset,
            weather.currentWeather.sys.sunset
        )
        assertEquals(
            weatherEntity.currentWeather.dt,
            weather.currentWeather.dt
        )
        assertEquals(
            weatherEntity.currentWeather.name,
            weather.currentWeather.name
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].dt,
            weather.extendedWeather.list[0].dt
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].main.temp,
            weather.extendedWeather.list[0].main.temp,
            0.0
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].main.pressure,
            weather.extendedWeather.list[0].main.pressure
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].main.humidity,
            weather.extendedWeather.list[0].main.humidity
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].dtTxt,
            weather.extendedWeather.list[0].dtTxt
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].weather[0].description,
            weather.extendedWeather.list[0].weather[0].description
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].weather[0].icon,
            weather.extendedWeather.list[0].weather[0].icon
        )
        assertEquals(
            weatherEntity.extendedWeather.list[0].wind.speed,
            weather.extendedWeather.list[0].wind.speed,
            0.0
        )
    }
}