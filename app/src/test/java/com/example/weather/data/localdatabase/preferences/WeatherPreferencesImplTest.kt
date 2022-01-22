package com.example.weather.data.localdatabase.preferences

import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class WeatherPreferencesImplTest {
    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var weatherPreferences: WeatherPreferences

    @Before
    fun init() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        weatherPreferences = WeatherPreferencesImpl(sharedPreferences)
    }

    @Test
    fun `saveCityName should not save if cityName is null`() {
        weatherPreferences.saveCityName(null)
        verify(sharedPreferencesEditor, never()).putString(any(), any())
    }

    @Test
    fun `saveCityName should not save if cityName is empty`() {
        weatherPreferences.saveCityName("")
        verify(sharedPreferencesEditor, never()).putString(any(), any())
    }

    @Test
    fun `saveCityName should save if cityName is not null or empty`() {
        val cityName = "Tbilisi"
        weatherPreferences.saveCityName(cityName)
        verify(sharedPreferencesEditor).putString(any(), eq(cityName))
    }

    @Test
    fun `getCityName should call sharedPreferences getString`() {
        weatherPreferences.getCityName()
        verify(sharedPreferences).getString(any(), any())
    }
}