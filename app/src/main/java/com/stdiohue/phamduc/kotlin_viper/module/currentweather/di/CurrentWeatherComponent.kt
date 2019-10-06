package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.kotlin_viper.module.currentweather.CurrentWeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.CurrentWeatherPresenter
import dagger.Subcomponent
import io.reactivex.subjects.PublishSubject

@Subcomponent(modules = [(CurrentWeatherModule::class)])
interface CurrentWeatherComponent {
    fun getCurrentWeatherAction(): PublishSubject<CurrentWeatherAction>

    fun getCurrentWeatherPresenter(): CurrentWeatherPresenter
}