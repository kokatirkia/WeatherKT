package com.example.weather.data.localdatabase.model

data class ExtendedWeatherEntity(
    val list: List<WeatherExtendedDataEntity>
)

data class WeatherExtendedDataEntity(
    val dt: Long,
    val mainEntity: MainExtendedEntity,
    val dt_txt: String,
    val weather: List<DescriptionExtendedEntity>,
    val windEntity: WindExtendedEntity
)

data class WindExtendedEntity(val speed: Double)

data class DescriptionExtendedEntity(
    val description: String,
    val icon: String
)

data class MainExtendedEntity(
    val temp: Double,
    val pressure: Int,
    val humidity: Int
)