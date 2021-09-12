package com.example.weather.ui.model.mapper

import com.example.weather.domain.model.Weather
import com.example.weather.ui.model.*
import com.example.weather.utils.DateUtil

fun Weather.toWeatherUi() = WeatherUi(
    currentWeatherUi = CurrentWeatherUi(
        weather = currentWeather.weatherDescription.map {
            WeatherDescriptionUi(
                main = it.main,
                description = it.description.capitalize(),
                icon = "${it.icon}.png"
            )
        },
        main = MainUi(
            temp = "${currentWeather.main.temp} °C",
            feelsLike = "Feels like ${currentWeather.main.feelsLike} °C",
            pressure = "${currentWeather.main.pressure} mBar",
            humidity = "${currentWeather.main.humidity} %"
        ),
        wind = WindUi(speed = "${currentWeather.wind.speed} km/h"),
        sys = SysUi(
            sunrise = DateUtil.longToString(currentWeather.sys.sunrise, "hh:mm a"),
            sunset = DateUtil.longToString(currentWeather.sys.sunset, "hh:mm a")
        ),
        day = DateUtil.longToString(currentWeather.dt, "EEEE"),
        time = DateUtil.longToString(currentWeather.dt, "hh:mm a"),
        name = currentWeather.name
    ),
    extendedWeatherUi = ExtendedWeatherUi(
        list = extendedWeather.list.map {
            WeatherExtendedDataUi(
                dt = DateUtil.longToString(it.dt, "EEE, d MMM HH:mm a"),
                main = MainExtendedUi(
                    temp = "${it.main.temp} °C",
                    pressure = "${it.main.pressure} mBar",
                    humidity = "${it.main.humidity} %"
                ),
                dtTxt = it.dtTxt,
                weather = it.weather.map { description ->
                    DescriptionExtendedUi(
                        description = description.description.capitalize(),
                        icon = "${description.icon}.png"
                    )
                },
                wind = WindExtendedUi("${it.wind.speed} km/h"),
                isExpanded = false
            )
        }
    )
)