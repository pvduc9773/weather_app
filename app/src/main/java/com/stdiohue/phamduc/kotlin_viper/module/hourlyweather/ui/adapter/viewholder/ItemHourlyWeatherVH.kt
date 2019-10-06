package com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.stdiohue.phamduc.data.model.CurrentWeather
import com.stdiohue.phamduc.kotlin_viper.utils.BitmapHelper
import com.stdiohue.phamduc.kotlin_viper.utils.DateTimeUltils
import com.stdiohue.phamduc.kotlin_viper.utils.setTemperature
import kotlinx.android.synthetic.main.item_hourly_weather.view.*

class ItemHourlyWeatherVH(var view: View) : RecyclerView.ViewHolder(view) {

    fun bind(weather: CurrentWeather) {
        view.tvSumary.text = weather.summary
        view.tvTemperature.setTemperature(weather.temperature.toDouble())

        view.tvTime.text = DateTimeUltils.longToDate(weather.time)
        BitmapHelper.bindWeatherIcon(weather.icon, view.ivWeatherIcon, null, view.context)
    }
}