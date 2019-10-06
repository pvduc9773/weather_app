package com.stdiohue.phamduc.data.model

class CurrentWeather {
    var time: Long = 0
    var summary: String = ""
    var icon: String = ""
    var precipIntensity: Int = 0
    var precipProbability: Int = 0
    var temperature: Int = 0
    var apparentTemperature: Double = 0.0
    var dewPoint: Double = 0.0
    var humidity: Double = 0.0
    var pressure: Double = 0.0
    var windSpeed: Double = 0.0
    var windGust: Double = 0.0
    var windBearing: Int = 0
    var cloudCover: Double = 0.0
    var uvIndex: Int = 0
    var visibility: Double = 0.0
    var ozone: Double = 0.0
    var precipType: String = ""
}