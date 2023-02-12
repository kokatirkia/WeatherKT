package com.example.weather.ui.model.mapper

import com.example.weather.data.repository.WeatherFactory
import com.example.weather.utils.DateUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class UiWeatherMapperTest {
    @Test
    fun testDomainToUiMapping() {
        val weather = WeatherFactory.makeWeather()
        val weatherUi = weather.toWeatherUi()

        assertEquals(
            weather.currentWeather.weather[0].main,
            weatherUi.currentWeatherUi.weather[0].main,
        )
        assertEquals(
            weather.currentWeather.weather[0].description.replaceFirstChar(Char::uppercase),
            weatherUi.currentWeatherUi.weather[0].description,
        )
        assertEquals(
            "${weather.currentWeather.weather[0].icon}.png",
            weatherUi.currentWeatherUi.weather[0].icon
        )
        assertEquals(
            "${weather.currentWeather.main.temp} °C",
            weatherUi.currentWeatherUi.main.temp,
        )
        assertEquals(
            "Feels like ${weather.currentWeather.main.feelsLike} °C",
            weatherUi.currentWeatherUi.main.feelsLike,
        )
        assertEquals(
            "${weather.currentWeather.main.pressure} mBar",
            weatherUi.currentWeatherUi.main.pressure,
        )
        assertEquals(
            "${weather.currentWeather.main.humidity} %",
            weatherUi.currentWeatherUi.main.humidity,
        )
        assertEquals(
            "${weather.currentWeather.wind.speed} km/h",
            weatherUi.currentWeatherUi.wind.speed,
        )
        assertEquals(
            DateUtil.longToString(weather.currentWeather.sys.sunrise, "hh:mm a"),
            weatherUi.currentWeatherUi.sys.sunrise,
        )
        assertEquals(
            DateUtil.longToString(weather.currentWeather.sys.sunset, "hh:mm a"),
            weatherUi.currentWeatherUi.sys.sunset,
        )
        assertEquals(
            weather.currentWeather.name,
            weather.currentWeather.name
        )
        assertEquals(
            DateUtil.longToString(weather.extendedWeather.list[0].dt, "EEE, d MMM HH:mm a"),
            weatherUi.extendedWeatherUi.list[0].dt,
        )
        assertEquals(
            "${weather.extendedWeather.list[0].main.temp} °C",
            weatherUi.extendedWeatherUi.list[0].main.temp,
        )
        assertEquals(
            "${weather.extendedWeather.list[0].main.pressure} mBar",
            weatherUi.extendedWeatherUi.list[0].main.pressure,
        )
        assertEquals(
            "${weather.extendedWeather.list[0].main.humidity} %",
            weatherUi.extendedWeatherUi.list[0].main.humidity,
        )
        assertEquals(
            weather.extendedWeather.list[0].dtTxt,
            weather.extendedWeather.list[0].dtTxt,
        )
        assertEquals(
            weather.extendedWeather.list[0].weather[0].description.replaceFirstChar(Char::uppercase),
            weatherUi.extendedWeatherUi.list[0].weather[0].description,
        )
        assertEquals(
            "${weather.extendedWeather.list[0].weather[0].icon}.png",
            weatherUi.extendedWeatherUi.list[0].weather[0].icon,
        )
        assertEquals(
            "${weather.extendedWeather.list[0].wind.speed} km/h",
            weatherUi.extendedWeatherUi.list[0].wind.speed,
        )
    }
}