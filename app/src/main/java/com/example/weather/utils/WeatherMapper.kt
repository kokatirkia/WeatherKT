package com.example.weather.utils

import com.example.weather.domain.model.*
import com.example.weather.data.localdatabase.model.*
import com.example.weather.data.networking.model.CurrentWeatherApi
import com.example.weather.data.networking.model.ExtendedWeatherApi
import com.example.weather.ui.model.*
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

    fun weatherDomainToWeatherUi(weather: Weather): WeatherUi {
        return WeatherUi(
            currentWeatherDomainToCurrentWeatherUi(weather.currentWeather),
            extendedWeatherDomainToExtendedWeatherUi(weather.extendedWeather)
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
                currentWeatherEntity.sysEntity.sunrise,
                currentWeatherEntity.sysEntity.sunset
            ),
            currentWeatherEntity.name
        )
    }

    private fun extendedWeatherEntityToExtendedWeatherDomain(extendedWeatherEntity: ExtendedWeatherEntity): ExtendedWeather {
        return ExtendedWeather(
            extendedWeatherEntity.list.map {
                WeatherExtendedData(
                    it.dt,
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
                currentWeatherApi.main.feels_like,
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
                    it.dt_txt,
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

    private fun currentWeatherDomainToCurrentWeatherUi(currentWeather: CurrentWeather): CurrentWeatherUi {
        return CurrentWeatherUi(
            currentWeather.weatherDescription.map {
                WeatherDescriptionUi(it.main, it.description, it.icon)
            },
            MainUi(
                currentWeather.main.temp,
                currentWeather.main.feels_like,
                currentWeather.main.pressure,
                currentWeather.main.humidity
            ),
            WindUi(currentWeather.wind.speed),
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
                        it.main.temp,
                        it.main.pressure,
                        it.main.humidity
                    ),
                    it.dt_txt,
                    it.weather.map { description ->
                        DescriptionExtendedUi(
                            description.description,
                            description.icon
                        )
                    },
                    WindExtendedUi(it.wind.speed),
                    expanded = false
                )
            }
        )
    }
}