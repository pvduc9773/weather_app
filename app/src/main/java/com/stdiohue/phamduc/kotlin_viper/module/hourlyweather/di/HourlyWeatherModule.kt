package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.data.usecase.GetHourlyWeatherUseCase
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.HourlyWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.HourlyWeatherPresenterImpl
import com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.HourlyWeatherAction
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@Module
class HourlyWeatherModule @Inject constructor(){
    @Provides
    fun providesHourlyWeatherAction(): PublishSubject<HourlyWeatherAction> {
        return HourlyWeatherAction.publisher
    }

    @Provides
    fun providesHourlyWeatherPresenter(weatherUseCase: GetHourlyWeatherUseCase, action: PublishSubject<HourlyWeatherAction>): HourlyWeatherPresenter {
        return HourlyWeatherPresenterImpl(weatherUseCase, action)
    }
}