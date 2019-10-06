package com.stdiohue.phamduc.kotlin_viper.di.components

import android.content.Context
import com.stdiohue.phamduc.kotlin_viper.ProjectApplication
import com.stdiohue.phamduc.kotlin_viper.di.modules.AppModule
import com.stdiohue.phamduc.kotlin_viper.managers.LocationManager
import com.stdiohue.phamduc.kotlin_viper.managers.SettingManager
import com.stdiohue.phamduc.kotlin_viper.module.home.di.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(projectApplication: ProjectApplication)

    fun getWeatherComponent(): WeatherComponent

    fun getLocationManager(): LocationManager

    fun getSettingManager(): SettingManager

    fun getCurrentWeatherComponent(): CurrentWeatherComponent

    fun getHourlyWeatherComponent(): HourlyWeatherComponent

    fun getDailyWeatherComponent(): DailyWeatherComponent

    fun getSettingComponent(): SettingComponent
}