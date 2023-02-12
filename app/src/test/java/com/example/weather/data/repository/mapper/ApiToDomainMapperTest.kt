package com.example.weather.data.repository.mapper

import com.example.weather.data.repository.WeatherApiFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiToDomainMapperTest {
    @Test
    fun test() {
        val weatherResponse = WeatherApiFactory.makeWeatherResponse()
        val weatherDomain = weatherResponse.toWeatherDomain()

        assertEquals(WeatherApiFactory.main, weatherDomain.currentWeather.weather[0].main)
        assertEquals(
            WeatherApiFactory.description,
            weatherDomain.currentWeather.weather[0].description
        )
        assertEquals(WeatherApiFactory.icon, weatherDomain.currentWeather.weather[0].icon)
        assertEquals(WeatherApiFactory.temp, weatherDomain.currentWeather.main.temp, 0.0)
        assertEquals(WeatherApiFactory.feelsLike, weatherDomain.currentWeather.main.feelsLike)
        assertEquals(WeatherApiFactory.pressure, weatherDomain.currentWeather.main.pressure)
        assertEquals(WeatherApiFactory.humidity, weatherDomain.currentWeather.main.humidity)
        assertEquals(WeatherApiFactory.speed, weatherDomain.currentWeather.wind.speed, 0.0)
        assertEquals(WeatherApiFactory.sunrise, weatherDomain.currentWeather.sys.sunrise)
        assertEquals(WeatherApiFactory.sunset, weatherDomain.currentWeather.sys.sunset)
        assertEquals(WeatherApiFactory.dt, weatherDomain.currentWeather.dt)
        assertEquals(WeatherApiFactory.name, weatherDomain.currentWeather.name)
        assertEquals(WeatherApiFactory.dt, weatherDomain.extendedWeather.list[0].dt)
        assertEquals(WeatherApiFactory.temp, weatherDomain.extendedWeather.list[0].main.temp, 0.0)
        assertEquals(
            WeatherApiFactory.pressure,
            weatherDomain.extendedWeather.list[0].main.pressure
        )
        assertEquals(
            WeatherApiFactory.humidity,
            weatherDomain.extendedWeather.list[0].main.humidity
        )
        assertEquals(WeatherApiFactory.dtTxt, weatherDomain.extendedWeather.list[0].dtTxt)
        assertEquals(
            WeatherApiFactory.description,
            weatherDomain.extendedWeather.list[0].weather[0].description
        )
        assertEquals(WeatherApiFactory.icon, weatherDomain.extendedWeather.list[0].weather[0].icon)
        assertEquals(WeatherApiFactory.speed, weatherDomain.extendedWeather.list[0].wind.speed, 0.0)
    }
}

