package com.stdiohue.phamduc.kotlin_viper.managers

import com.stdiohue.phamduc.data.model.Setting

interface SettingManager {
    interface OnSettingChangeListener {
        fun onSettingChange(setting: Setting)
    }

    fun addOnSettingChangeListener(listener: OnSettingChangeListener)

    fun removeSettingChangeListener(listener: OnSettingChangeListener)

    fun storeSetting(setting: Setting)

    fun storeTemperatureSetting(temperature: Boolean)

    fun getSetting(): Setting
}