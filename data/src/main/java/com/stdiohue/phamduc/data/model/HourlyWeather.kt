package com.stdiohue.phamduc.data.model

class HourlyWeather {
    var summary: String = ""
    var icon: String = ""
    lateinit var data: List<CurrentWeather>
}