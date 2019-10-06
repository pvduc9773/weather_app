package com.stdiohue.phamduc.data.model

class Weather {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var timezone: String = ""
    lateinit var currently: CurrentWeather
    var offset: String = ""
    lateinit var daily: DailyWeather
}