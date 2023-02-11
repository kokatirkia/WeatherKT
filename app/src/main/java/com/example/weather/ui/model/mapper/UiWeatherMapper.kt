package com.example.weather.ui.model.mapper

import com.example.weather.domain.model.Weather
import com.example.weather.ui.model.CurrentWeatherUi
import com.example.weather.ui.model.ExtendedWeatherUi
import com.example.weather.ui.model.WeatherUi
import com.example.weather.utils.DateUtil

fun Weather.toWeatherUi() = WeatherUi(
    currentWeatherUi = CurrentWeatherUi(
        weather = currentWeather.weather.map {
            CurrentWeatherUi.Weather(
                main = it.main,
                description = it.description.replaceFirstChar(Char::uppercase),
                icon = "${it.icon}.png"
            )
        },
        main = CurrentWeatherUi.Main(
            temp = "${currentWeather.main.temp} °C",
            feelsLike = "Feels like ${currentWeather.main.feelsLike} °C",
            pressure = "${currentWeather.main.pressure} mBar",
            humidity = "${currentWeather.main.humidity} %"
        ),
        wind = CurrentWeatherUi.Wind(speed = "${currentWeather.wind.speed} km/h"),
        sys = CurrentWeatherUi.Sys(
            sunrise = DateUtil.longToString(currentWeather.sys.sunrise, "hh:mm a"),
            sunset = DateUtil.longToString(currentWeather.sys.sunset, "hh:mm a")
        ),
        day = DateUtil.longToString(currentWeather.dt, "EEEE"),
        time = DateUtil.longToString(currentWeather.dt, "hh:mm a"),
        name = currentWeather.name
    ),
    extendedWeatherUi = ExtendedWeatherUi(
        list = extendedWeather.list.map {
            ExtendedWeatherUi.WeatherItem(
                dt = DateUtil.longToString(it.dt, "EEE, d MMM HH:mm a"),
                main = ExtendedWeatherUi.Main(
                    temp = "${it.main.temp} °C",
                    pressure = "${it.main.pressure} mBar",
                    humidity = "${it.main.humidity} %"
                ),
                dtTxt = it.dtTxt,
                weather = it.weather.map { description ->
                    ExtendedWeatherUi.Weather(
                        description = description.description.replaceFirstChar(Char::uppercase),
                        icon = "${description.icon}.png"
                    )
                },
                wind = ExtendedWeatherUi.Wind("${it.wind.speed} km/h"),
            )
        }
    )
)