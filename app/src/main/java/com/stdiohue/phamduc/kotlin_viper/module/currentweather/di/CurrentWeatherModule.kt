package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.data.usecase.GetCurrentWeatherUseCase
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.CurrentWeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.CurrentWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.CurrentWeatherPresenterImpl
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
class CurrentWeatherModule {
    @Provides
    fun providesProductDetailAction(): PublishSubject<CurrentWeatherAction> {
        return CurrentWeatherAction.publisher
    }

    @Provides
    fun providesCurrentWeatherPresenter(weatherUseCase: GetCurrentWeatherUseCase, action: PublishSubject<CurrentWeatherAction>): CurrentWeatherPresenter {
        return CurrentWeatherPresenterImpl(weatherUseCase, action)
    }
}