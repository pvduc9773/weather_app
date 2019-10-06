package com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter

import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter

interface DailyWeatherPresenter: Presenter {
    fun getWeather(lat:Double, lng:Double)
}