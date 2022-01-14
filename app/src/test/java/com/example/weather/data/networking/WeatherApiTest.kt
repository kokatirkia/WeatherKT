package com.example.weather.data.networking

import com.example.weather.utils.Constants
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherApiTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    private lateinit var retrofit: Retrofit
    private lateinit var weatherApi: WeatherApi
    private val city = "Tbilisi"
    private val units = Constants.units
    private val apiKey = Constants.ApiKey
    private val timeout = 2000L

    @Before
    fun setup() {
        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.close()
    }

    @Test
    fun `getCurrentWeather method should be GET`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(WeatherApiJsonFactory.currentWeatherJson())
                .setResponseCode(200)
        )

        weatherApi.getCurrentWeather(
            city,
            units,
            apiKey
        )

        assertEquals(mockWebServer.takeRequest(timeout, TimeUnit.MILLISECONDS)?.method, "GET")
    }

    @Test
    fun `getCurrentWeather path should be valid`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(WeatherApiJsonFactory.currentWeatherJson())
                .setResponseCode(200)
        )

        weatherApi.getCurrentWeather(
            city,
            units,
            apiKey
        )

        assertEquals(
            mockWebServer.takeRequest(timeout, TimeUnit.MILLISECONDS)?.path,
            "/weather?q=$city&units=$units&appid=$apiKey"
        )
    }

    @Test
    fun `getCurrentWeather returns currentWeatherApi`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(WeatherApiJsonFactory.currentWeatherJson())
                .setResponseCode(200)
        )

        val currentWeatherApi = weatherApi.getCurrentWeather(
            city,
            units,
            apiKey
        )

        assertEquals(currentWeatherApi.name, WeatherApiJsonFactory.name)
        assertEquals(currentWeatherApi.dt, WeatherApiJsonFactory.dt)
        assertEquals(currentWeatherApi.main.temp, WeatherApiJsonFactory.temp)
        assertEquals(currentWeatherApi.main.feelsLike, WeatherApiJsonFactory.feelsLike)
        assertEquals(currentWeatherApi.main.pressure, WeatherApiJsonFactory.pressure)
        assertEquals(currentWeatherApi.main.humidity, WeatherApiJsonFactory.humidity)
        assertEquals(currentWeatherApi.wind.speed, WeatherApiJsonFactory.speed)
        assertEquals(currentWeatherApi.sys.sunrise, WeatherApiJsonFactory.sunrise)
        assertEquals(currentWeatherApi.sys.sunset, WeatherApiJsonFactory.sunset)
        assertEquals(currentWeatherApi.weather[0].main, WeatherApiJsonFactory.main)
        assertEquals(currentWeatherApi.weather[0].description, WeatherApiJsonFactory.description)
        assertEquals(currentWeatherApi.weather[0].icon, WeatherApiJsonFactory.icon)
    }

    @Test
    fun `getExtendedWeather method should be GET`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(WeatherApiJsonFactory.extendedWeatherJson())
                .setResponseCode(200)
        )

        weatherApi.getExtendedWeather(
            city,
            units,
            apiKey
        )

        assertEquals(mockWebServer.takeRequest(timeout, TimeUnit.MILLISECONDS)?.method, "GET")
    }

    @Test
    fun `getExtendedWeather path should be valid`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(WeatherApiJsonFactory.extendedWeatherJson())
                .setResponseCode(200)
        )

        weatherApi.getExtendedWeather(
            city,
            units,
            apiKey
        )

        assertEquals(
            mockWebServer.takeRequest(timeout, TimeUnit.MILLISECONDS)?.path,
            "/forecast?q=$city&units=$units&appid=$apiKey"
        )
    }

    @Test
    fun `getExtendedWeather returns extendedWeatherApi`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(WeatherApiJsonFactory.extendedWeatherJson())
                .setResponseCode(200)
        )

        val extendedWeatherApi = weatherApi.getExtendedWeather(
            city,
            units,
            apiKey
        )

        assertEquals(extendedWeatherApi.message, WeatherApiJsonFactory.message)
        assertEquals(extendedWeatherApi.list[0].dt, WeatherApiJsonFactory.dt)
        assertEquals(extendedWeatherApi.list[0].main.temp, WeatherApiJsonFactory.temp)
        assertEquals(extendedWeatherApi.list[0].main.pressure, WeatherApiJsonFactory.pressure)
        assertEquals(extendedWeatherApi.list[0].main.humidity, WeatherApiJsonFactory.humidity)
        assertEquals(extendedWeatherApi.list[0].wind.speed, WeatherApiJsonFactory.speed)
        assertEquals(extendedWeatherApi.list[0].weather[0].icon, WeatherApiJsonFactory.icon)
        assertEquals(
            extendedWeatherApi.list[0].weather[0].description, WeatherApiJsonFactory.description
        )
    }
}