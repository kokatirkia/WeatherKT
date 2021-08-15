package com.example.weather.utils

enum class ResponseMessageEnum(val value: String) {
    NoInternetConnection("No Internet Connection"),
    CityNotFound("City not found!"),
    ErrorWhileFetching("Error fetching data"),
}