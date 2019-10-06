package com.stdiohue.phamduc.kotlin_viper.module.home

import com.stdiohue.phamduc.data.model.Weather
import io.reactivex.subjects.PublishSubject


class WeatherAction {
    companion object {
        var publisher: PublishSubject<WeatherAction> = PublishSubject.create()

        fun getWeatherComplete(weather: Weather): WeatherAction {
            val action = WeatherAction()
            action.weather = weather
            return action
        }
    }

    var isLoading: Boolean = false
    var errorMessage: String? = null
    lateinit var weather: Weather

    fun isLoading(isLoading: Boolean): WeatherAction {
        val action = WeatherAction()
        action.isLoading = isLoading
        return action
    }

    fun error(mess: String): WeatherAction {
        val action = WeatherAction()
        action.errorMessage = mess
        return action
    }
}