package com.example.weather.data.repository.mapper

import com.example.weather.data.localdatabase.model.*
import com.example.weather.data.networking.model.CurrentWeatherApi
import com.example.weather.data.networking.model.ExtendedWeatherApi
import com.example.weather.domain.model.*
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun weatherApiToWeatherDomain(
        currentWeatherApi: CurrentWeatherApi,
        extendedWeatherApi: ExtendedWeatherApi
    ): Weather {
        return Weather(
            currentWeather = currentWeatherApiToCurrentWeatherDomain(currentWeatherApi),
            extendedWeather = extendedWeatherApiToExtendedWeatherDomain(extendedWeatherApi)
        )
    }

    private fun currentWeatherApiToCurrentWeatherDomain(currentWeatherApi: CurrentWeatherApi): CurrentWeather {
        return CurrentWeather(
            currentWeatherApi.weather.map {
                WeatherDescription(it.main, it.description, it.icon)
            },
            Main(
                currentWeatherApi.main.temp,
                currentWeatherApi.main.feelsLike,
                currentWeatherApi.main.pressure,
                currentWeatherApi.main.humidity
            ),
            Wind(currentWeatherApi.wind.speed),
            Sys(
                currentWeatherApi.sys.sunrise,
                currentWeatherApi.sys.sunset
            ),
            currentWeatherApi.dt,
            currentWeatherApi.name
        )
    }

    private fun extendedWeatherApiToExtendedWeatherDomain(extendedWeatherApi: ExtendedWeatherApi): ExtendedWeather {
        return ExtendedWeather(
            extendedWeatherApi.list.map {
                WeatherExtendedData(
                    it.dt,
                    MainExtended(it.main.temp, it.main.pressure, it.main.humidity),
                    it.dtTxt,
                    it.weather.map { description ->
                        DescriptionExtended(
                            description.description,
                            description.icon
                        )
                    },
                    WindExtended(it.wind.speed)
                )
            }
        )
    }

    fun weatherEntityToWeatherDomain(weatherEntity: WeatherEntity): Weather {
        return Weather(
            currentWeather = currentWeatherEntityToCurrentWeatherDomain(weatherEntity.currentWeather),
            extendedWeather = extendedWeatherEntityToExtendedWeatherDomain(weatherEntity.extendedWeather)
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
            currentWeatherEntity.dt,
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

    fun weatherDomainToWeatherEntity(weather: Weather): WeatherEntity {
        return WeatherEntity(
            currentWeather = currentWeatherDomainToCurrentWeatherEntity(weather.currentWeather),
            extendedWeather = extendedWeatherDomainToExtendedWeatherEntity(weather.extendedWeather)
        )
    }

    private fun currentWeatherDomainToCurrentWeatherEntity(currentWeather: CurrentWeather): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            currentWeather.weatherDescription.map {
                WeatherDescriptionEntity(it.main, it.description, it.icon)
            },
            MainEntity(
                currentWeather.main.temp,
                currentWeather.main.feelsLike,
                currentWeather.main.pressure,
                currentWeather.main.humidity
            ),
            WindEntity(currentWeather.wind.speed),
            SysEntity(
                currentWeather.sys.sunrise,
                currentWeather.sys.sunset
            ),
            currentWeather.dt,
            currentWeather.name
        )
    }

    private fun extendedWeatherDomainToExtendedWeatherEntity(extendedWeather: ExtendedWeather): ExtendedWeatherEntity {
        return ExtendedWeatherEntity(
            extendedWeather.list.map {
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