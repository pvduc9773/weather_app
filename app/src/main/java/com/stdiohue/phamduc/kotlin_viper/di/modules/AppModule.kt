package com.stdiohue.phamduc.kotlin_viper.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stdiohue.phamduc.data.di.DataModule
import com.stdiohue.phamduc.kotlin_viper.managers.LocalLocationManager
import com.stdiohue.phamduc.kotlin_viper.managers.LocalSettingManager
import com.stdiohue.phamduc.kotlin_viper.managers.LocationManager
import com.stdiohue.phamduc.kotlin_viper.managers.SettingManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(DataModule::class)])
class AppModule {
    @Provides
    @Singleton
    @Named("default")
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("weather", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesLocationManager(sharedPreferences: SharedPreferences, gson: Gson): LocationManager {
        return LocalLocationManager(sharedPreferences, gson)
    }

    @Provides
    @Singleton
    fun providesSettingManager(sharedPreferences: SharedPreferences, gson: Gson): SettingManager {
        return LocalSettingManager(sharedPreferences, gson)
    }
}