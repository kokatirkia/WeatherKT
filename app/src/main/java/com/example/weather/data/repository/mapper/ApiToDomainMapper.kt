package com.example.weather.data.repository.mapper

import com.example.weather.data.networking.model.WeatherModelApi
import com.example.weather.domain.model.*

fun WeatherModelApi.toWeatherDomain() = Weather(
    currentWeather = CurrentWeather(
        weatherDescription = currentWeatherApi.weather.map {
            WeatherDescription(
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        main = Main(
            temp = currentWeatherApi.main.temp,
            feelsLike = currentWeatherApi.main.feelsLike,
            pressure = currentWeatherApi.main.pressure,
            humidity = currentWeatherApi.main.humidity
        ),
        wind = Wind(speed = currentWeatherApi.wind.speed),
        sys = Sys(
            sunrise = currentWeatherApi.sys.sunrise,
            sunset = currentWeatherApi.sys.sunset
        ),
        dt = currentWeatherApi.dt,
        name = currentWeatherApi.name
    ),
    extendedWeather = ExtendedWeather(
        list = extendedWeatherApi.list.map {
            WeatherExtendedData(
                dt = it.dt,
                main = MainExtended(
                    temp = it.main.temp,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity
                ),
                dtTxt = it.dtTxt,
                weather = it.weather.map { description ->
                    DescriptionExtended(
                        description = description.description,
                        icon = description.icon
                    )
                },
                wind = WindExtended(speed = it.wind.speed)
            )
        }
    )
)