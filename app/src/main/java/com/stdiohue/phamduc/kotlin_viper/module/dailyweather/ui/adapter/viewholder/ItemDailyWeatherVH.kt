package com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.stdiohue.phamduc.data.model.DailyWeatherData
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.adapter.DailyWeatherAdapter
import com.stdiohue.phamduc.kotlin_viper.utils.BitmapHelper
import com.stdiohue.phamduc.kotlin_viper.utils.DateTimeUltils
import com.stdiohue.phamduc.kotlin_viper.utils.setTemperature
import kotlinx.android.synthetic.main.item_daily_weather.view.*

class ItemDailyWeatherVH(var view: View, var listener: DailyWeatherAdapter.ItemDailyWeatherListener) : RecyclerView.ViewHolder(view) {
    fun bind(weather: DailyWeatherData) {
        view.tvSumary.text = weather.summary
        view.tvTemperatureHight.setTemperature(weather.temperatureHigh)
        view.tvTemperatureLow.setTemperature(weather.temperatureLow)
        view.tvTime.text = DateTimeUltils.longToDateName(weather.time)
        BitmapHelper.bindWeatherIcon(weather.icon, view.ivWeatherIcon, null, view.context)

        view.clParent.setOnClickListener { listener.onItemWeatherClick(weather) }
    }
}