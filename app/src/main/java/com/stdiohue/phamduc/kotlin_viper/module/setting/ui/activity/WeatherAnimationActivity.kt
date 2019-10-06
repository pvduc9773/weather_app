package com.stdiohue.phamduc.kotlin_viper.module.setting.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppActivity
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.BasePresenter
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.module.setting.SettingAction
import com.stdiohue.phamduc.kotlin_viper.module.setting.ui.adapter.WeatherAnimationPagerAdapter
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_weather_animation.*

class WeatherAnimationActivity : BaseAppActivity<Presenter>() {
    private lateinit var adapter: WeatherAnimationPagerAdapter
    private lateinit var action: PublishSubject<SettingAction>

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, WeatherAnimationActivity::class.java))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_weather_animation
    }

    override fun init() {
        adapter = WeatherAnimationPagerAdapter(supportFragmentManager)
        action = getAppComponent().getSettingComponent().getSettingAction()
        vpAnimation.adapter = adapter
        vpAnimation.offscreenPageLimit = 1

        setEvent()

        vpAnimation.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                when (p0) {
                    0 -> {
                        setTitle(getString(R.string.clear_day))
                    }
                    1 -> {
                        setTitle(getString(R.string.clear_night))
                    }
                    2 -> {
                        setTitle(getString(R.string.rain))
                    }
                    3 -> {
                        setTitle(getString(R.string.snow))
                    }
                    4 -> {
                        setTitle(getString(R.string.sleet))
                    }
                    5 -> {
                        setTitle(getString(R.string.wind))
                    }
                    6 -> {
                        setTitle(getString(R.string.fog))
                    }
                    7 -> {
                        setTitle(getString(R.string.cloudy))
                    }
                    8 -> {
                        setTitle(getString(R.string.partly_cloudy_day))
                    }
                    9 -> {
                        setTitle(getString(R.string.partly_cloudy_night))
                    }
                    10 -> {
                        setTitle(getString(R.string.thunderstorm))
                    }
                }
            }
        })
    }

    private fun setTitle(title: String) {
        tvTitle.text = title
    }

    private fun setEvent() {
        ivBack.setOnClickListener { finish() }
    }

    override fun startScreen() {
    }

    override fun resumeScreen() {
    }

    override fun pauseScreen() {
    }

    override fun destroyScreen() {
    }

    override fun createPresenter(): Presenter {
        return BasePresenter()
    }
}
