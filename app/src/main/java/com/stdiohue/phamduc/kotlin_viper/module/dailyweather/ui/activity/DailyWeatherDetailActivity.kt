package com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.activity

import android.content.Context
import android.content.Intent
import com.stdiohue.phamduc.data.model.DailyWeatherData
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppActivity
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.BasePresenter
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.utils.BitmapHelper
import com.stdiohue.phamduc.kotlin_viper.utils.DateTimeUltils
import com.stdiohue.phamduc.kotlin_viper.utils.StringUtils
import kotlinx.android.synthetic.main.activity_daily_weather_detail.*

class DailyWeatherDetailActivity : BaseAppActivity<Presenter>() {
    private lateinit var weather: DailyWeatherData
    private var stringUtils = StringUtils(this)

    companion object {
        private var DETAIL = "detail"

        fun startActivity(context: Context, weather: DailyWeatherData) {
            var intent = Intent(context, DailyWeatherDetailActivity::class.java)
            intent.putExtra(DETAIL, weather)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_daily_weather_detail
    }

    override fun init() {
        weather = intent.getSerializableExtra(DETAIL) as DailyWeatherData

        setEvent()
        setData()
    }

    private fun setData() {
        tvTitle.text = DateTimeUltils.longToDateName(weather.time)

        BitmapHelper.bindWeatherIcon(weather.icon, ivWeatherIcon, ivWeatherImage, this)

        tvSumary.text = weather.summary
        tvTemperatureHigh.text = stringUtils.setTemperature(weather.temperatureHigh)
        tvTemperatureLow.text = stringUtils.setTemperature(weather.temperatureLow)
        wvSunriseTime.setValue(DateTimeUltils.longToDate(weather.sunriseTime))
        wvSunsetTime.setValue(DateTimeUltils.longToDate(weather.sunsetTime))
        wvMoonPhase.setValue(weather.moonPhase.toString())
        wvTemperatureHighTime.setValue(DateTimeUltils.longToDate(weather.temperatureHighTime.toLong()))
        wvTemperatureLowTime.setValue(DateTimeUltils.longToDate(weather.temperatureLowTime.toLong()))
        wvUvIndex.setValue(weather.uvIndex.toString())
        wvApparentTemperatureHigh.setValue(stringUtils.setTemperature(weather.apparentTemperatureHigh))
        wvApparentTemperatureLow.setValue(stringUtils.setTemperature(weather.apparentTemperatureLow))
        wvDewPoint.setValue(stringUtils.setDegrees(weather.dewPoint.toString()))
        wvHumidity.setValue(weather.humidity.toString())
        wvPressure.setValue(stringUtils.setPressure(weather.pressure.toString()))
        wvWindGust.setValue(stringUtils.setSpeed(weather.windGust.toString()))
        wvWindSpeed.setValue(stringUtils.setSpeed(weather.windSpeed.toString()))
        wvWindBearing.setValue(stringUtils.setDegrees(weather.windBearing.toString()))
        wvCloudCover.setValue(stringUtils.setPercentage(weather.cloudCover.toString()))
        wvOzone.setValue(stringUtils.setOzone(weather.ozone.toString()))
        wvVisibility.setValue(stringUtils.setLength(weather.visibility.toString()))
        wvPrecipIntensity.setValue(stringUtils.setIntensity(weather.precipIntensity.toString()))
        wvPrecipProbability.setValue(weather.precipProbability.toString())
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
