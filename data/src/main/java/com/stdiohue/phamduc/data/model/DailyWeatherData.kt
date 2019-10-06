package com.stdiohue.phamduc.data.model

import java.io.Serializable

class DailyWeatherData : Serializable{
    var time: Long = 0
    var summary: String = ""
    var icon: String = ""
    var sunriseTime: Long = 0
    var sunsetTime: Long = 0
    var moonPhase: Double = 0.0
    var precipIntensity: Double = 0.0
    var precipIntensityMax: Double = 0.0
    var precipIntensityMaxTime: Double = 0.0
    var precipProbability: Double = 0.0
    var precipType: String = ""
    var temperatureHigh: Double = 0.0
    var temperatureHighTime: Int = 0
    var temperatureLow: Double = 0.0
    var temperatureLowTime: Int = 0
    var apparentTemperatureHigh: Double = 0.0
    var apparentTemperatureHighTime: Int = 0
    var apparentTemperatureLow: Double = 0.0
    var apparentTemperatureLowTime: Int = 0
    var dewPoint: Double = 0.0
    var humidity: Double = 0.0
    var pressure: Double = 0.0
    var windSpeed: Double = 0.0
    var windGust: Double = 0.0
    var windGustTime: Long = 0
    var windBearing: Long = 0
    var cloudCover: Double = 0.0
    var uvIndex: Int = 0
    var uvIndexTime: Long = 0
    var visibility: Int = 0
    var ozone: Double = 0.0
    var temperatureMin: Double = 0.0
    var temperatureMinTime: Long = 0
    var temperatureMax: Double = 0.0
    var temperatureMaxTime: Long = 0
    var apparentTemperatureMin: Double = 0.0
    var apparentTemperatureMinTime: Long = 0
    var apparentTemperatureMax: Double = 0.0
    var apparentTemperatureMaxTime: Long = 0
}