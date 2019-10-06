package com.stdiohue.phamduc.kotlin_viper.module.home.di

import com.stdiohue.phamduc.kotlin_viper.module.setting.SettingAction
import dagger.Subcomponent
import io.reactivex.subjects.PublishSubject

@Subcomponent(modules = [(SettingModule::class)])
interface SettingComponent {
    fun getSettingAction(): PublishSubject<SettingAction>
}