package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.kotlin_viper.module.home.WeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.home.presenter.WeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.home.presenter.WeatherPresenterImpl
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
class WeatherModule {
    @Provides
    fun providesProductDetailAction(): PublishSubject<WeatherAction> {
        return WeatherAction.publisher
    }

    @Provides
    fun providesWeatherPresenter(): WeatherPresenter {
        return WeatherPresenterImpl()
    }
}