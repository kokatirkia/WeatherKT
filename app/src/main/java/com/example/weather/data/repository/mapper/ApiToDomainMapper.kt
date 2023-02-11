package com.example.weather.data.repository.mapper

import com.example.weather.data.networking.model.WeatherResponse
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ExtendedWeather
import com.example.weather.domain.model.Weather

fun WeatherResponse.toWeatherDomain() = Weather(
    currentWeather = CurrentWeather(
        weather = currentWeatherApi.weather.map {
            CurrentWeather.Weather(
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        main = CurrentWeather.Main(
            temp = currentWeatherApi.main.temp,
            feelsLike = currentWeatherApi.main.feelsLike,
            pressure = currentWeatherApi.main.pressure,
            humidity = currentWeatherApi.main.humidity
        ),
        wind = CurrentWeather.Wind(speed = currentWeatherApi.wind.speed),
        sys = CurrentWeather.Sys(
            sunrise = currentWeatherApi.sys.sunrise,
            sunset = currentWeatherApi.sys.sunset
        ),
        dt = currentWeatherApi.dt,
        name = currentWeatherApi.name
    ),
    extendedWeather = ExtendedWeather(
        list = extendedWeatherApi.list.map {
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
                wind = ExtendedWeather.Wind(speed = it.wind.speed)
            )
        }
    )
)