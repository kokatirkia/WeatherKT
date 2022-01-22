package com.example.weather.data.localdatabase.preferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : WeatherPreferences {
    private val cityKey = "city"
    private val defaultCityName = "Tbilisi"

    override fun saveCityName(cityName: String?) {
        if(!cityName.isNullOrEmpty()){
            val editor = sharedPreferences.edit()
            editor.putString(cityKey, cityName)
            editor.apply()
        }
    }

    override fun getCityName(): String {
        return sharedPreferences.getString(cityKey, defaultCityName).toString()
    }
}