package com.example.weather.data.repository.mapper

import com.example.weather.data.localdatabase.model.*
import com.example.weather.data.networking.model.CurrentWeatherApi
import com.example.weather.data.networking.model.ExtendedWeatherApi
import com.example.weather.domain.model.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun weatherApiToWeatherEntity(
        currentWeatherApi: CurrentWeatherApi,
        extendedWeatherApi: ExtendedWeatherApi
    ): WeatherEntity {
        return WeatherEntity(
            currentWeather = currentWeatherApiToCurrentWeatherEntity(currentWeatherApi),
            extendedWeather = extendedWeatherApiToExtendedWeatherEntity(extendedWeatherApi)
        )
    }

    fun weatherEntityToWeatherDomain(weatherEntity: WeatherEntity): Weather {
        return Weather(
            currentWeatherEntityToCurrentWeatherDomain(weatherEntity.currentWeather),
            extendedWeatherEntityToExtendedWeatherDomain(weatherEntity.extendedWeather)
        )
    }

    private fun currentWeatherEntityToCurrentWeatherDomain(currentWeatherEntity: CurrentWeatherEntity): CurrentWeather {
        return CurrentWeather(
            currentWeatherEntity.weatherDescriptionEntity.map {
                WeatherDescription(
                    it.main,
                    it.description,
                    it.icon
                )
            },
            Main(
                currentWeatherEntity.mainEntity.temp,
                currentWeatherEntity.mainEntity.feels_like,
                currentWeatherEntity.mainEntity.pressure,
                currentWeatherEntity.mainEntity.humidity
            ),
            Wind(currentWeatherEntity.windEntity.speed),
            Sys(
                SimpleDateFormat(
                    "hh:mm a",
                    Locale.ENGLISH
                ).format(Date(currentWeatherEntity.sysEntity.sunrise * 1000)),
                SimpleDateFormat(
                    "hh:mm a",
                    Locale.ENGLISH
                ).format(Date(currentWeatherEntity.sysEntity.sunset * 1000))
            ),
            currentWeatherEntity.name
        )
    }

    private fun extendedWeatherEntityToExtendedWeatherDomain(extendedWeatherEntity: ExtendedWeatherEntity): ExtendedWeather {
        return ExtendedWeather(
            extendedWeatherEntity.list.map {
                WeatherExtendedData(
                    SimpleDateFormat(
                        "EEE, d MMM HH:mm",
                        Locale.ENGLISH
                    ).format(Date(it.dt * 1000)),
                    MainExtended(
                        it.mainEntity.temp,
                        it.mainEntity.pressure,
                        it.mainEntity.humidity
                    ),
                    it.dt_txt,
                    it.weather.map { description ->
                        DescriptionExtended(
                            description.description,
                            description.icon
                        )
                    },
                    WindExtended(it.windEntity.speed)
                )
            }
        )
    }

    private fun currentWeatherApiToCurrentWeatherEntity(currentWeatherApi: CurrentWeatherApi): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            currentWeatherApi.weather.map {
                WeatherDescriptionEntity(it.main, it.description, it.icon)
            },
            MainEntity(
                currentWeatherApi.main.temp,
                currentWeatherApi.main.feelsLike,
                currentWeatherApi.main.pressure,
                currentWeatherApi.main.humidity
            ),
            WindEntity(currentWeatherApi.wind.speed),
            SysEntity(
                currentWeatherApi.sys.sunrise,
                currentWeatherApi.sys.sunset
            ),
            currentWeatherApi.name
        )
    }

    private fun extendedWeatherApiToExtendedWeatherEntity(extendedWeatherApi: ExtendedWeatherApi): ExtendedWeatherEntity {
        return ExtendedWeatherEntity(
            extendedWeatherApi.message,
            extendedWeatherApi.list.map {
                WeatherExtendedDataEntity(
                    it.dt,
                    MainExtendedEntity(it.main.temp, it.main.pressure, it.main.humidity),
                    it.dtTxt,
                    it.weather.map { description ->
                        DescriptionExtendedEntity(
                            description.description,
                            description.icon
                        )
                    },
                    WindExtendedEntity(it.wind.speed)
                )
            }
        )
    }
}