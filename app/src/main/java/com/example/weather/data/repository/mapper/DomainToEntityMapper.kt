package com.example.weather.data.repository.mapper

import com.example.weather.data.localdatabase.model.*
import com.example.weather.domain.model.Weather

fun Weather.toWeatherEntity() = WeatherEntity(
    currentWeather = CurrentWeatherEntity(
        weatherDescriptionEntity = currentWeather.weatherDescription.map {
            WeatherDescriptionEntity(
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        mainEntity = MainEntity(
            temp = currentWeather.main.temp,
            feels_like = currentWeather.main.feelsLike,
            pressure = currentWeather.main.pressure,
            humidity = currentWeather.main.humidity
        ),
        windEntity = WindEntity(speed = currentWeather.wind.speed),
        sysEntity = SysEntity(
            sunrise = currentWeather.sys.sunrise,
            sunset = currentWeather.sys.sunset
        ),
        dt = currentWeather.dt,
        name = currentWeather.name
    ),
    extendedWeather = ExtendedWeatherEntity(
        list = extendedWeather.list.map {
            WeatherExtendedDataEntity(
                dt = it.dt,
                mainEntity = MainExtendedEntity(it.main.temp, it.main.pressure, it.main.humidity),
                dt_txt = it.dtTxt,
                weather = it.weather.map { description ->
                    DescriptionExtendedEntity(
                        description = description.description,
                        icon = description.icon
                    )
                },
                windEntity = WindExtendedEntity(it.wind.speed)
            )
        }
    )
)