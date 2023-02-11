package com.example.weather.data.repository.mapper

import com.example.weather.data.localdatabase.model.CurrentWeatherEntity
import com.example.weather.data.localdatabase.model.ExtendedWeatherEntity
import com.example.weather.data.localdatabase.model.WeatherEntity
import com.example.weather.domain.model.Weather

fun Weather.toWeatherEntity() = WeatherEntity(
    currentWeather = CurrentWeatherEntity(
        weather = currentWeather.weather.map {
            CurrentWeatherEntity.Weather(
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        main = CurrentWeatherEntity.Main(
            temp = currentWeather.main.temp,
            feels_like = currentWeather.main.feelsLike,
            pressure = currentWeather.main.pressure,
            humidity = currentWeather.main.humidity
        ),
        wind = CurrentWeatherEntity.Wind(speed = currentWeather.wind.speed),
        sys = CurrentWeatherEntity.Sys(
            sunrise = currentWeather.sys.sunrise,
            sunset = currentWeather.sys.sunset
        ),
        dt = currentWeather.dt,
        name = currentWeather.name
    ),
    extendedWeather = ExtendedWeatherEntity(
        list = extendedWeather.list.map {
            ExtendedWeatherEntity.WeatherItem(
                dt = it.dt,
                main = ExtendedWeatherEntity.Main(
                    it.main.temp,
                    it.main.pressure,
                    it.main.humidity
                ),
                dtTxt = it.dtTxt,
                weather = it.weather.map { description ->
                    ExtendedWeatherEntity.Weather(
                        description = description.description,
                        icon = description.icon
                    )
                },
                wind = ExtendedWeatherEntity.Wind(it.wind.speed)
            )
        }
    )
)