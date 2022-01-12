package com.example.weather.domain.usecases

import com.example.weather.data.repository.WeatherFactory
import com.example.weather.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class FetchWeatherFromApiUseCaseTest {
    @Mock
    private lateinit var repository: Repository
    private lateinit var fetchWeatherFromApiUseCase: FetchWeatherFromApiUseCase
    private val cityName = "Tbilisi"

    @Before
    fun setup() {
        fetchWeatherFromApiUseCase = FetchWeatherFromApiUseCase(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApiUseCase should call repository fetchWeatherFromApi and then saveWeatherInLocalDatabase`() =
        runBlockingTest {
            val weatherDomain = WeatherFactory.makeWeather()
            whenever(repository.fetchWeatherFromApi(cityName)).thenReturn(weatherDomain)
            fetchWeatherFromApiUseCase.invoke(cityName)

            inOrder(repository) {
                verify(repository).fetchWeatherFromApi(cityName)
                verify(repository).saveWeatherInLocalDatabase(weatherDomain)
            }
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApiUseCase when IOException is thrown should return NoInternetConnection`() =
        runBlockingTest {
            given(repository.fetchWeatherFromApi(cityName)).willAnswer { throw IOException() }
            val response = fetchWeatherFromApiUseCase.invoke(cityName)

            assert(response == ErrorMessageEnum.NoInternetConnection)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApiUseCase when HttpException is thrown should return CityNotFound`() =
        runBlockingTest {
            given(repository.fetchWeatherFromApi(cityName)).willAnswer {
                throw HttpException(
                    Response.error<ResponseBody>(
                        500,
                        "some content".toResponseBody("plain/text".toMediaTypeOrNull())
                    )
                )
            }
            val response = fetchWeatherFromApiUseCase.invoke(cityName)

            assert(response == ErrorMessageEnum.CityNotFound)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchWeatherFromApiUseCase when Exception is thrown should return ErrorWhileFetching`() =
        runBlockingTest {
            given(repository.fetchWeatherFromApi(cityName)).willAnswer { throw Exception() }
            val response = fetchWeatherFromApiUseCase.invoke(cityName)

            assert(response == ErrorMessageEnum.ErrorWhileFetching)
        }
}