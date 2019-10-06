package com.stdiohue.phamduc.kotlin_viper.module.currentweather

import com.stdiohue.phamduc.data.response.CurrentWeatherResponse
import io.reactivex.subjects.PublishSubject


class CurrentWeatherAction {
    var isLoading: Boolean = false
    var errorMessage: String? = null
    lateinit var weather: CurrentWeatherResponse

    companion object {
        var publisher: PublishSubject<CurrentWeatherAction> = PublishSubject.create()

        fun getWeatherComplete(weather: CurrentWeatherResponse): CurrentWeatherAction {
            val action = CurrentWeatherAction()
            action.weather = weather
            return action
        }

        fun isLoading(isLoading: Boolean): CurrentWeatherAction {
            val action = CurrentWeatherAction()
            action.isLoading = isLoading
            return action
        }

        fun error(mess: String): CurrentWeatherAction {
            val action = CurrentWeatherAction()
            action.errorMessage = mess
            return action
        }
    }
}