package com.example.weather.data.repository.mapper

import com.example.weather.data.repository.WeatherApiFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiToDomainMapperTest {
    @Test
    fun testApiToDomainMapping() {
        val weatherResponse = WeatherApiFactory.makeWeatherResponse()
        val weatherDomain = weatherResponse.toWeatherDomain()

        assertEquals(
            weatherResponse.currentWeatherResponse.weather[0].main,
            weatherDomain.currentWeather.weather[0].main
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.weather[0].description,
            weatherDomain.currentWeather.weather[0].description
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.weather[0].icon,
            weatherDomain.currentWeather.weather[0].icon
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.main.temp,
            weatherDomain.currentWeather.main.temp,
            0.0
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.main.feelsLike,
            weatherDomain.currentWeather.main.feelsLike
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.main.pressure,
            weatherDomain.currentWeather.main.pressure
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.main.humidity,
            weatherDomain.currentWeather.main.humidity
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.wind.speed,
            weatherDomain.currentWeather.wind.speed,
            0.0
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.sys.sunrise,
            weatherDomain.currentWeather.sys.sunrise
        )
        assertEquals(
            weatherResponse.currentWeatherResponse.sys.sunset,
            weatherDomain.currentWeather.sys.sunset
        )
        assertEquals(weatherResponse.currentWeatherResponse.dt, weatherDomain.currentWeather.dt)
        assertEquals(weatherResponse.currentWeatherResponse.name, weatherDomain.currentWeather.name)
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].dt,
            weatherDomain.extendedWeather.list[0].dt
        )
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].main.temp,
            weatherDomain.extendedWeather.list[0].main.temp,
            0.0
        )
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].main.pressure,
            weatherDomain.extendedWeather.list[0].main.pressure
        )
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].main.humidity,
            weatherDomain.extendedWeather.list[0].main.humidity
        )
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].dtTxt,
            weatherDomain.extendedWeather.list[0].dtTxt
        )
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].weather[0].description,
            weatherDomain.extendedWeather.list[0].weather[0].description
        )
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].weather[0].icon,
            weatherDomain.extendedWeather.list[0].weather[0].icon
        )
        assertEquals(
            weatherResponse.extendedWeatherResponse.list[0].wind.speed,
            weatherDomain.extendedWeather.list[0].wind.speed,
            0.0
        )
    }
}

