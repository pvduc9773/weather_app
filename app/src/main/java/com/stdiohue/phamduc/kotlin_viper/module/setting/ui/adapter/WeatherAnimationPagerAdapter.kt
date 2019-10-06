package com.stdiohue.phamduc.kotlin_viper.module.setting.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.stdiohue.phamduc.kotlin_viper.module.setting.ui.fragment.WeatherAnimationFragment

class WeatherAnimationPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private var ANIMATION_COUNT: Int = 11

    override fun getItem(p0: Int): Fragment {
        return WeatherAnimationFragment.newInstance(p0)
    }

    override fun getCount(): Int {
        return ANIMATION_COUNT
    }
}