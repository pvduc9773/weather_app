package com.stdiohue.phamduc.kotlin_viper.module.dailyweather

import com.stdiohue.phamduc.data.response.DailyWeatherResponse
import io.reactivex.subjects.PublishSubject


class DailyWeatherAction {
    companion object {
        var publisher: PublishSubject<DailyWeatherAction> = PublishSubject.create()

        fun getWeatherComplete(weather: DailyWeatherResponse): DailyWeatherAction {
            val action = DailyWeatherAction()
            action.weather = weather
            return action
        }
    }

    var isLoading: Boolean = false
    var errorMessage: String? = null
    lateinit var weather: DailyWeatherResponse

    fun isLoading(isLoading: Boolean): DailyWeatherAction {
        val action = DailyWeatherAction()
        action.isLoading = isLoading
        return action
    }

    fun error(mess: String): DailyWeatherAction {
        val action = DailyWeatherAction()
        action.errorMessage = mess
        return action
    }
}