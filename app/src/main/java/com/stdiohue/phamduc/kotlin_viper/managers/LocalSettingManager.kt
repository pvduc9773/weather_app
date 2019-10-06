package com.stdiohue.phamduc.kotlin_viper.managers

import android.content.SharedPreferences
import com.google.gson.Gson
import com.stdiohue.phamduc.data.model.Setting
import java.util.*

class LocalSettingManager(var sharedPreferences: SharedPreferences, var gson: Gson) : SettingManager {
    private var SETTING = "setting"

    override fun storeTemperatureSetting(temperature: Boolean) {
        var setting: Setting = getSetting()
        setting.temperature = temperature
        storeSetting(setting)
    }

    private var observer = ArrayList<SettingManager.OnSettingChangeListener>()

    override fun addOnSettingChangeListener(listener: SettingManager.OnSettingChangeListener) {
        observer.add(listener)
    }

    override fun removeSettingChangeListener(listener: SettingManager.OnSettingChangeListener) {
        observer.remove(listener)
    }

    override fun storeSetting(setting: Setting) {
        sharedPreferences.edit().putString(SETTING, gson.toJson(setting)).apply()
        notifyUserChange(setting)
    }

    override fun getSetting(): Setting {
        return gson.fromJson(sharedPreferences.getString(SETTING, ""), Setting::class.java)
                ?: Setting()
    }

    private fun notifyUserChange(setting: Setting) {
        if (observer != null && !observer.isEmpty()) {
            for (listener in observer) {
                listener.onSettingChange(setting)
            }
        }
    }
}