package com.example.weather.data.networking

object WeatherApiJsonFactory {
    const val main = "Clear"
    const val description = "clear"
    const val icon = "01n"
    const val temp = 8.84
    const val feelsLike = "8.84"
    const val pressure = 1018
    const val humidity = 49
    const val speed = 8.23
    const val dt = 1641391386L
    const val sunrise = 1641356867L
    const val sunset = 1641390223L
    const val name: String = "Tbilisi"
    const val message = 0.0

    fun currentWeatherJson() =
        """{"weather":[{"main":$main,"description":$description,"icon":$icon}],
            "main":{"temp":$temp,"feels_like":$feelsLike,"pressure":$pressure,"humidity":$humidity},
            "wind":{"speed":$speed},"dt":$dt,"sys":{"sunrise":$sunrise,"sunset":$sunset},"name":$name}"""


    fun extendedWeatherJson(): String =
        """{"message":$message,"list":[{"dt":$dt,
            "main":{"temp":$temp,"pressure":$pressure,"humidity":$humidity},
            "weather":[{"description":$description,"icon":$icon}],"clouds":{"all":87},
            "wind":{"speed":$speed},"visibility":10000,"pop":0,"sys":{"pod":"d"},
            "dt_txt":"2022-01-10 12:00:00"}]}"""
}