package com.example.weather.data.localdatabase.preferences

import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherPreferencesImplIntegrationTest {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var weatherPreferences: WeatherPreferences

    @Before
    fun init() {
        sharedPreferences =
            InstrumentationRegistry.getInstrumentation().context.getSharedPreferences("test", 0)
        sharedPreferencesEditor = sharedPreferences.edit()

        weatherPreferences = WeatherPreferencesImpl(sharedPreferences)
    }

    @After
    fun tearDown() {
        sharedPreferencesEditor.clear().commit()
    }

    @Test
    fun insertCityNameAndGetBack() {
        val cityName = "Tbilisi"
        weatherPreferences.saveCityName(cityName)

        val returnedCityName = weatherPreferences.getCityName()
        assertEquals(cityName, returnedCityName)
    }

    @Test
    fun getCityNameShouldReturn_Tbilisi_asCityNameIfPrefIsEmpty() {
        assertEquals("Tbilisi", weatherPreferences.getCityName())
    }
}