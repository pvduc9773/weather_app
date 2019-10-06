package com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.stdiohue.phamduc.data.model.DailyWeatherData
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.adapter.viewholder.ItemDailyWeatherVH

class DailyWeatherAdapter(var listener: ItemDailyWeatherListener) : RecyclerView.Adapter<ItemDailyWeatherVH>() {
    private var items: List<DailyWeatherData> = ArrayList()

    fun updateAdapter(items: List<DailyWeatherData>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDailyWeatherVH {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_weather, null)
        view.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        return ItemDailyWeatherVH(view, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemDailyWeatherVH, position: Int) {
        holder.bind(items[position])
    }

    interface ItemDailyWeatherListener {
        fun onItemWeatherClick(weather: DailyWeatherData)
    }
}