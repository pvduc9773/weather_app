package com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter

import com.stdiohue.phamduc.data.usecase.GetCurrentWeatherUseCase
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.BasePresenter
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.CurrentWeatherAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class CurrentWeatherPresenterImpl(var weatherUseCase: GetCurrentWeatherUseCase, var action: PublishSubject<CurrentWeatherAction>) : BasePresenter(), CurrentWeatherPresenter {
    override fun getWeather(lat: Double, lng: Double) {
        weatherUseCase.execute(lat, lng)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    action.onNext(CurrentWeatherAction.getWeatherComplete(response))
                }, { e -> action.onNext(CurrentWeatherAction.error(e.message!!)) })
    }
}