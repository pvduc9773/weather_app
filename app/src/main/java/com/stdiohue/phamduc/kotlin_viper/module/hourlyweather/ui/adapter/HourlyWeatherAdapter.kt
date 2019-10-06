package com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.stdiohue.phamduc.data.model.CurrentWeather
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.ui.adapter.viewholder.ItemHourlyWeatherVH

class HourlyWeatherAdapter : RecyclerView.Adapter<ItemHourlyWeatherVH>() {
    private var items: List<CurrentWeather> = ArrayList()

    fun updateAdapter(items: List<CurrentWeather>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHourlyWeatherVH {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_weather, null)
        view.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        return ItemHourlyWeatherVH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHourlyWeatherVH, position: Int) {
        holder.bind(items[position])
    }
}