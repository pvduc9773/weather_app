package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.data.usecase.GetDailyWeatherUseCase
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.DailyWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.DailyWeatherPresenterImpl
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.DailyWeatherAction
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
class DailyWeatherModule {
    @Provides
    fun providesProductDetailAction(): PublishSubject<DailyWeatherAction> {
        return DailyWeatherAction.publisher
    }

    @Provides
    fun providesDailyWeatherPresenter(weatherUseCase: GetDailyWeatherUseCase, action: PublishSubject<DailyWeatherAction>): DailyWeatherPresenter {
        return DailyWeatherPresenterImpl(weatherUseCase, action)
    }
}