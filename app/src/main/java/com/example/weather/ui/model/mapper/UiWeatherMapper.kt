package com.example.weather.ui.model.mapper

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ExtendedWeather
import com.example.weather.domain.model.Weather
import com.example.weather.ui.model.*
import com.example.weather.utils.DateUtil
import javax.inject.Inject

class UiWeatherMapper @Inject constructor(private val dateUtil: DateUtil) {

    fun weatherDomainToWeatherUi(weather: Weather?): WeatherUi? {
        return weather?.let {
            return WeatherUi(
                currentWeatherUi = currentWeatherDomainToCurrentWeatherUi(it.currentWeather),
                extendedWeatherUi = extendedWeatherDomainToExtendedWeatherUi(it.extendedWeather)
            )
        }
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
                "Feels like ${currentWeather.main.feelsLike} °C",
                "${currentWeather.main.pressure} mBar",
                "${currentWeather.main.humidity} %"
            ),
            WindUi("${currentWeather.wind.speed} km/h"),
            SysUi(
                dateUtil.longToString(currentWeather.sys.sunrise, "hh:mm a"),
                dateUtil.longToString(currentWeather.sys.sunset, "hh:mm a")
            ),
            dateUtil.longToString(currentWeather.dt, "EEEE"),
            dateUtil.longToString(currentWeather.dt, "hh:mm a"),
            currentWeather.name
        )
    }

    private fun extendedWeatherDomainToExtendedWeatherUi(extendedWeather: ExtendedWeather): ExtendedWeatherUi {
        return ExtendedWeatherUi(
            extendedWeather.list.map {
                WeatherExtendedDataUi(
                    dateUtil.longToString(it.dt, "EEE, d MMM HH:mm a"),
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