package com.stdiohue.phamduc.kotlin_viper.module.home.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.ui.fragment.CurrentWeatherFragment
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.fragment.DailyWeatherFragment
import com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.ui.fragment.HourlyWeatherFragment

class HomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CurrentWeatherFragment.newInstance()
            1 -> HourlyWeatherFragment.newInstance()
            else -> DailyWeatherFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
