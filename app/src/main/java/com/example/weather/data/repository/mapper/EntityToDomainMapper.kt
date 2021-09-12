package com.example.weather.data.repository.mapper

import com.example.weather.data.localdatabase.model.WeatherEntity
import com.example.weather.domain.model.*

fun WeatherEntity.toWeatherDomain() = Weather(
    currentWeather = CurrentWeather(
        weatherDescription = currentWeather.weatherDescriptionEntity.map {
            WeatherDescription(
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        main = Main(
            temp = currentWeather.mainEntity.temp,
            feelsLike = currentWeather.mainEntity.feels_like,
            pressure = currentWeather.mainEntity.pressure,
            humidity = currentWeather.mainEntity.humidity
        ),
        wind = Wind(currentWeather.windEntity.speed),
        sys = Sys(
            sunrise = currentWeather.sysEntity.sunrise,
            sunset = currentWeather.sysEntity.sunset
        ),
        dt = currentWeather.dt,
        name = currentWeather.name
    ),
    extendedWeather = ExtendedWeather(
        list = extendedWeather.list.map {
            WeatherExtendedData(
                dt = it.dt,
                main = MainExtended(
                    temp = it.mainEntity.temp,
                    pressure = it.mainEntity.pressure,
                    humidity = it.mainEntity.humidity
                ),
                dtTxt = it.dt_txt,
                weather = it.weather.map { description ->
                    DescriptionExtended(
                        description = description.description,
                        icon = description.icon
                    )
                },
                wind = WindExtended(it.windEntity.speed)
            )
        }
    )
)