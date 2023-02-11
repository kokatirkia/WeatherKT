package com.example.weather.data.repository.mapper

import com.example.weather.data.localdatabase.model.WeatherEntity
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ExtendedWeather
import com.example.weather.domain.model.Weather

fun WeatherEntity.toWeatherDomain() = Weather(
    currentWeather = CurrentWeather(
        weather = currentWeather.weather.map {
            CurrentWeather.Weather(
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        main = CurrentWeather.Main(
            temp = currentWeather.main.temp,
            feelsLike = currentWeather.main.feels_like,
            pressure = currentWeather.main.pressure,
            humidity = currentWeather.main.humidity
        ),
        wind = CurrentWeather.Wind(currentWeather.wind.speed),
        sys = CurrentWeather.Sys(
            sunrise = currentWeather.sys.sunrise,
            sunset = currentWeather.sys.sunset
        ),
        dt = currentWeather.dt,
        name = currentWeather.name
    ),
    extendedWeather = ExtendedWeather(
        list = extendedWeather.list.map {
            ExtendedWeather.WeatherItem(
                dt = it.dt,
                main = ExtendedWeather.Main(
                    temp = it.main.temp,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity
                ),
                dtTxt = it.dtTxt,
                weather = it.weather.map { description ->
                    ExtendedWeather.Weather(
                        description = description.description,
                        icon = description.icon
                    )
                },
                wind = ExtendedWeather.Wind(it.wind.speed)
            )
        }
    )
)