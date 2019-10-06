package com.stdiohue.phamduc.data.model

class DailyWeather {
    var summary: String = ""
    var icon: String = ""
    lateinit var data: List<DailyWeatherData>
}