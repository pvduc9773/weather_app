package com.stdiohue.phamduc.kotlin_viper.module.hourlyweather

import com.stdiohue.phamduc.data.response.HourlyWeatherResponse
import io.reactivex.subjects.PublishSubject


class HourlyWeatherAction {
    companion object {
        var publisher: PublishSubject<HourlyWeatherAction> = PublishSubject.create()

        fun getWeatherComplete(weather: HourlyWeatherResponse): HourlyWeatherAction {
            val action = HourlyWeatherAction()
            action.weather = weather
            return action
        }

        fun isLoading(isLoading: Boolean): HourlyWeatherAction {
            val action = HourlyWeatherAction()
            action.isLoading = isLoading
            return action
        }

        fun error(mess: String): HourlyWeatherAction {
            val action = HourlyWeatherAction()
            action.errorMessage = mess
            return action
        }
    }

    var isLoading: Boolean = false
    var errorMessage: String? = null
    lateinit var weather: HourlyWeatherResponse
}