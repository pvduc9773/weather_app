package com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter

import com.stdiohue.phamduc.data.usecase.GetHourlyWeatherUseCase
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.BasePresenter
import com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.HourlyWeatherAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class HourlyWeatherPresenterImpl(var weatherUseCase: GetHourlyWeatherUseCase, var action: PublishSubject<HourlyWeatherAction>) : BasePresenter(), HourlyWeatherPresenter {
    override fun getWeather(lat: Double, lng: Double) {
        weatherUseCase.execute(lat, lng)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    action.onNext(HourlyWeatherAction.getWeatherComplete(response))
                }, { e -> action.onNext(HourlyWeatherAction.error(e.message!!)) })
    }
}