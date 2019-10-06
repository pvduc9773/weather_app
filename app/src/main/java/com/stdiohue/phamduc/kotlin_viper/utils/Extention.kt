package com.stdiohue.phamduc.kotlin_viper.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.stdiohue.phamduc.data.model.Setting
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseActivity

class Extention(var setting: Setting) {
    fun ImageView.bindImage(imageId: Int) {
        Glide.with(context)
                .load(imageId)
                .into(this)
    }
}

fun BaseActivity.showLogIfNeed(isValid: Boolean, method :() -> Unit){
    if (isValid){
        method.invoke()
    } else {
        print("deo")
    }
}

@SuppressLint("SetTextI18n")
fun TextView.setTemperature(degrees: Double) {
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
        this.text = (((degrees.toInt() - 32) * 5) / 9).toString() + 0x00B0.toChar() + "C"
    } else {
        this.text = degrees.toInt().toString() + 0x00B0.toChar() + "F"
    }
}

fun TextView.setPressure(pressure: String) {
    this.text = "$pressure mbar"
}

fun TextView.setSpeed(speed: String) {
    this.text = "$speed mph"
}

fun TextView.setDegrees(degrees: String) {
    this.text = degrees + 0x00B0.toChar()
}

fun TextView.setPercentage(percentage: String) {
    this.text = "$percentage%"
}

fun TextView.setOzone(ozone: String) {
    this.text = "$ozone DU"
}

fun TextView.setLength(length: String) {
    this.text = "$length mile"
}

fun TextView.setIntensity(intensity: String) {
    this.text = "$intensity mm/h"
}

