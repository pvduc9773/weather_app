package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.DailyWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.DailyWeatherAction
import dagger.Subcomponent
import io.reactivex.subjects.PublishSubject


@Subcomponent(modules = [(DailyWeatherModule::class)])
interface DailyWeatherComponent {
    fun getDailyWeatherAction(): PublishSubject<DailyWeatherAction>

    fun getDailyWeatherPresenter(): DailyWeatherPresenter
}