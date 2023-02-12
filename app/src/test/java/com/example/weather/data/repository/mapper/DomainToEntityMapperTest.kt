package com.example.weather.data.repository.mapper

import com.example.weather.data.repository.WeatherFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class DomainToEntityMapperTest {
    @Test
    fun testDomainToEntityMapping() {
        val weather = WeatherFactory.makeWeather()
        val weatherEntity = weather.toWeatherEntity()

        assertEquals(
            weather.currentWeather.weather[0].main,
            weatherEntity.currentWeather.weather[0].main
        )
        assertEquals(
            weather.currentWeather.weather[0].description,
            weatherEntity.currentWeather.weather[0].description
        )
        assertEquals(
            weather.currentWeather.weather[0].icon,
            weatherEntity.currentWeather.weather[0].icon
        )
        assertEquals(
            weather.currentWeather.main.temp,
            weatherEntity.currentWeather.main.temp,
            0.0
        )
        assertEquals(
            weather.currentWeather.main.feelsLike,
            weatherEntity.currentWeather.main.feels_like
        )
        assertEquals(
            weather.currentWeather.main.pressure,
            weatherEntity.currentWeather.main.pressure
        )
        assertEquals(
            weather.currentWeather.main.humidity,
            weatherEntity.currentWeather.main.humidity
        )
        assertEquals(
            weather.currentWeather.wind.speed,
            weatherEntity.currentWeather.wind.speed,
            0.0
        )
        assertEquals(
            weather.currentWeather.sys.sunrise,
            weatherEntity.currentWeather.sys.sunrise
        )
        assertEquals(
            weather.currentWeather.sys.sunset,
            weatherEntity.currentWeather.sys.sunset
        )
        assertEquals(
            weather.currentWeather.dt,
            weatherEntity.currentWeather.dt
        )
        assertEquals(
            weather.currentWeather.name,
            weatherEntity.currentWeather.name
        )
        assertEquals(
            weather.extendedWeather.list[0].dt,
            weatherEntity.extendedWeather.list[0].dt
        )
        assertEquals(
            weather.extendedWeather.list[0].main.temp,
            weatherEntity.extendedWeather.list[0].main.temp,
            0.0
        )
        assertEquals(
            weather.extendedWeather.list[0].main.pressure,
            weatherEntity.extendedWeather.list[0].main.pressure
        )
        assertEquals(
            weather.extendedWeather.list[0].main.humidity,
            weatherEntity.extendedWeather.list[0].main.humidity
        )
        assertEquals(
            weather.extendedWeather.list[0].dtTxt,
            weatherEntity.extendedWeather.list[0].dtTxt
        )
        assertEquals(
            weather.extendedWeather.list[0].weather[0].description,
            weatherEntity.extendedWeather.list[0].weather[0].description
        )
        assertEquals(
            weather.extendedWeather.list[0].weather[0].icon,
            weatherEntity.extendedWeather.list[0].weather[0].icon
        )
        assertEquals(
            weather.extendedWeather.list[0].wind.speed,
            weatherEntity.extendedWeather.list[0].wind.speed,
            0.0
        )
    }
}