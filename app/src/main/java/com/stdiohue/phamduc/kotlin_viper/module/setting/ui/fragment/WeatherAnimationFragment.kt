package com.stdiohue.phamduc.kotlin_viper.module.setting.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppFragment
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.BasePresenter
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.utils.VideoHelper
import kotlinx.android.synthetic.main.fragment_weather_animation.*

class WeatherAnimationFragment : BaseAppFragment<Presenter>() {
    private var position: Int = 0

    companion object {
        private var POSITION_VALUE = "position"

        fun newInstance(position: Int) = WeatherAnimationFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_VALUE, position)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_weather_animation
    }

    override fun init(view: View?) {
        setEvent()

        if (arguments != null) {
            position = arguments?.getInt(POSITION_VALUE)!!

            when (position) {
                0 -> {
                    setData(R.raw.clear_day_video)
                }
                1 -> {
                    setData(R.raw.clear_night_video)
                }
                2 -> {
                    setData(R.raw.rain_video)
                }
                3 -> {
                    setData(R.raw.snow_video)
                }
                4 -> {
                    setData(R.raw.snow_video)
                }
                5 -> {
                    setData(R.raw.wind_video)
                }
                6 -> {
                    setData(R.raw.fog_video)
                }
                7 -> {
                    setData(R.raw.cloudy_video)
                }
                8 -> {
                    setData(R.raw.partly_cloudy_day_video)
                }
                9 -> {
                    setData(R.raw.partly_cloudy_night_video)
                }
                10 -> {
                    setData(R.raw.thunderstorm_video)
                }
            }
        }
    }

    private fun setEvent() {
    }

    private fun setData(imageId: Int) {
        VideoHelper.playVideo(ivAnimation, imageId, context!!)
    }

    override fun screenResume() {
    }

    override fun screenPause() {
        ivAnimation.stopPlayback()
    }

    override fun screenStart(saveInstanceState: Bundle?) {
    }

    override fun attach(context: Context?) {
    }

    override fun createPresenter(): Presenter {
        return BasePresenter()
    }
}
