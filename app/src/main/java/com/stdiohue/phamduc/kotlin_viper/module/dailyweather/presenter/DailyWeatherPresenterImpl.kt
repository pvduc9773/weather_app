package com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter

import com.stdiohue.phamduc.data.usecase.GetDailyWeatherUseCase
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.BasePresenter
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.DailyWeatherAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class DailyWeatherPresenterImpl(var weatherUseCase: GetDailyWeatherUseCase, var action: PublishSubject<DailyWeatherAction>) : BasePresenter(), DailyWeatherPresenter {
    override fun getWeather(lat: Double, lng: Double) {
        weatherUseCase.execute(lat, lng)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    action.onNext(DailyWeatherAction.getWeatherComplete(response))
                }, { e -> action.onNext(DailyWeatherAction().error(e.message!!)) })
    }
}