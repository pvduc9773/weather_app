package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.kotlin_viper.module.home.WeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.home.presenter.WeatherPresenter
import dagger.Subcomponent
import io.reactivex.subjects.PublishSubject

@Subcomponent(modules = [(WeatherModule::class)])
interface WeatherComponent {
    fun getWeatherPresenter(): WeatherPresenter

    fun getWeatherAction(): PublishSubject<WeatherAction>
}