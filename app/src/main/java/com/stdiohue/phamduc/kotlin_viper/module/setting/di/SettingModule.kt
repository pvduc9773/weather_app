package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.kotlin_viper.module.setting.SettingAction
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject

@Module
class SettingModule {
    @Provides
    fun providesSettingAction(): PublishSubject<SettingAction> {
        return SettingAction.publisher
    }
}