package com.example.weather.data.repository.mapper

import com.example.weather.data.networking.model.WeatherResponse
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ExtendedWeather
import com.example.weather.domain.model.Weather

fun WeatherResponse.toWeatherDomain() = Weather(
    currentWeather = CurrentWeather(
        weather = currentWeatherResponse.weather.map {
            CurrentWeather.Weather(
                main = it.main,
                description = it.description,
                icon = it.icon
            )
        },
        main = CurrentWeather.Main(
            temp = currentWeatherResponse.main.temp,
            feelsLike = currentWeatherResponse.main.feelsLike,
            pressure = currentWeatherResponse.main.pressure,
            humidity = currentWeatherResponse.main.humidity
        ),
        wind = CurrentWeather.Wind(speed = currentWeatherResponse.wind.speed),
        sys = CurrentWeather.Sys(
            sunrise = currentWeatherResponse.sys.sunrise,
            sunset = currentWeatherResponse.sys.sunset
        ),
        dt = currentWeatherResponse.dt,
        name = currentWeatherResponse.name
    ),
    extendedWeather = ExtendedWeather(
        list = extendedWeatherResponse.list.map {
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