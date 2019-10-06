package com.stdiohue.phamduc.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stdiohue.phamduc.data.usecase.GetCurrentWeatherUseCase
import com.stdiohue.phamduc.data.usecase.GetDailyWeatherUseCase
import com.stdiohue.phamduc.data.usecase.GetHourlyWeatherUseCase
import com.stdiohue.phamduc.network.di.NetworkModule
import com.stdiohue.phamduc.network.services.WeatherServices
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataModule {
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun providesCurentWeatherUseCase(gson: Gson, weatherServices: WeatherServices): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(gson, weatherServices)
    }

    @Provides
    fun providesHourlyWeatherUseCase(gson: Gson, weatherServices: WeatherServices): GetHourlyWeatherUseCase {
        return GetHourlyWeatherUseCase(gson, weatherServices)
    }

    @Provides
    fun providesDailyWeatherUseCase(gson: Gson, weatherServices: WeatherServices): GetDailyWeatherUseCase {
        return GetDailyWeatherUseCase(gson, weatherServices)
    }
}