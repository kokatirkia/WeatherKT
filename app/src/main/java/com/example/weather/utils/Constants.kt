package com.example.weather.utils

//api url    https://api.openweathermap.org/data/2.5/weather?q=Tbilisi&units=metric&appid=64d498c46d9473b333472889203dac1d
//api url for 5 days    http://api.openweathermap.org/data/2.5/forecast?q=tbilisi&units=metric&appid=64d498c46d9473b333472889203dac1d
object Constants {
    const val BaseUrl = "https://api.openweathermap.org/data/2.5/"
    var CITY: String = "Tbilisi"
    const val ApiKey: String = "64d498c46d9473b333472889203dac1d"
    const val units = "metric"
    const val iconUrl = "https://openweathermap.org/img/wn/"
}