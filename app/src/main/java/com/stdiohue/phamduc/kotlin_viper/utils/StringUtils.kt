package com.stdiohue.phamduc.kotlin_viper.utils

import android.content.Context
import com.google.gson.Gson
import com.stdiohue.phamduc.data.model.Setting

class StringUtils(var context: Context) {

    fun setTemperature(degrees: Double): String {
        var gson = Gson()
        var temp: Boolean
        var setting: Setting?

        //todo need check flow

        setting = gson.fromJson(this.context.getSharedPreferences("weather", Context.MODE_PRIVATE).getString("setting", ""), Setting::class.java)

        if (setting == null) {
            temp = false
        } else {
            temp = setting.temperature
        }

        if (temp) {
            return (((degrees.toInt() - 32) * 5) / 9).toString() + 0x00B0.toChar() + "C"
        } else {
            return degrees.toInt().toString() + 0x00B0.toChar() + "F"
        }
    }

    fun setPressure(pressure: String): String {
        return "$pressure mbar"
    }

    fun setSpeed(speed: String): String {
        return "$speed mph"
    }

    fun setDegrees(degrees: String): String {
        return degrees + 0x00B0.toChar()
    }

    fun setPercentage(percentage: String): String {
        return "$percentage%"
    }

    fun setOzone(ozone: String): String {
        return "$ozone DU"
    }

    fun setLength(length: String): String {
        return "$length mile"
    }

    fun setIntensity(intensity: String): String {
        return "$intensity mm/h"
    }
}