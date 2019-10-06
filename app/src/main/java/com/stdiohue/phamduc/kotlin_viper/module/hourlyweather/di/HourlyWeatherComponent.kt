package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.HourlyWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.HourlyWeatherAction
import dagger.Subcomponent
import io.reactivex.subjects.PublishSubject

@Subcomponent(modules = [(HourlyWeatherModule::class)])
interface HourlyWeatherComponent {
    fun getHourlyWeatherAction(): PublishSubject<HourlyWeatherAction>

    fun getHourlyWeatherPresenter(): HourlyWeatherPresenter
}