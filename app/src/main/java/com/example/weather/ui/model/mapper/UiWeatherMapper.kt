package com.example.weather.ui.model.mapper

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ExtendedWeather
import com.example.weather.domain.model.Weather
import com.example.weather.ui.model.*
import javax.inject.Inject

class UiWeatherMapper @Inject constructor() {

    fun weatherDomainToWeatherUi(weather: Weather): WeatherUi {
        return WeatherUi(
            currentWeatherDomainToCurrentWeatherUi(weather.currentWeather),
            extendedWeatherDomainToExtendedWeatherUi(weather.extendedWeather)
        )
    }

    private fun currentWeatherDomainToCurrentWeatherUi(currentWeather: CurrentWeather): CurrentWeatherUi {
        return CurrentWeatherUi(
            currentWeather.weatherDescription.map {
                WeatherDescriptionUi(
                    it.main,
                    it.description.capitalize(),
                    "${it.icon}.png"
                )
            },
            MainUi(
                "${currentWeather.main.temp} °C",
                "${currentWeather.main.feelsLike} °C",
                "${currentWeather.main.pressure} mBar",
                "${currentWeather.main.humidity} %"
            ),
            WindUi("${currentWeather.wind.speed} km/h"),
            SysUi(
                currentWeather.sys.sunrise,
                currentWeather.sys.sunset
            ),
            currentWeather.name
        )
    }

    private fun extendedWeatherDomainToExtendedWeatherUi(extendedWeather: ExtendedWeather): ExtendedWeatherUi {
        return ExtendedWeatherUi(
            extendedWeather.list.map {
                WeatherExtendedDataUi(
                    it.dt,
                    MainExtendedUi(
                        "${it.main.temp} °C",
                        "${it.main.pressure} mBar",
                        "${it.main.humidity} %"
                    ),
                    it.dtTxt,
                    it.weather.map { description ->
                        DescriptionExtendedUi(
                            description.description.capitalize(),
                            "${description.icon}.png"
                        )
                    },
                    WindExtendedUi("${it.wind.speed} km/h"),
                    isExpanded = false
                )
            }
        )
    }
}