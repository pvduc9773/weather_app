package com.stdiohue.phamduc.kotlin_viper.module.currentweather.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.stdiohue.phamduc.data.model.CurrentWeather
import com.stdiohue.phamduc.data.model.Setting
import com.stdiohue.phamduc.data.model.UserLocation
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppFragment
import com.stdiohue.phamduc.kotlin_viper.managers.LocationManager
import com.stdiohue.phamduc.kotlin_viper.managers.SettingManager
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.CurrentWeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.CurrentWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.utils.*
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_current_weather.*


class CurrentWeatherFragment : BaseAppFragment<CurrentWeatherPresenter>(), LocationManager.OnLocationChangeListener, SettingManager.OnSettingChangeListener {
    private lateinit var locationManager: LocationManager
    private lateinit var action: PublishSubject<CurrentWeatherAction>
    private lateinit var slideDown: Animation
    private lateinit var slideUp: Animation
    private var isShow: Boolean = false
    private lateinit var settingManager: SettingManager
    private lateinit var currentWeather: CurrentWeather

    override fun onSettingChange(setting: Setting) {
        setWeatherData(currentWeather)
    }

    override fun onLocationChange(newLocation: UserLocation) {
        getPresenter().getWeather(newLocation.latitude, newLocation.longitude)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CurrentWeatherFragment().apply {}
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_current_weather
    }

    override fun init(view: View?) {
        action = getAppComponent().getCurrentWeatherComponent().getCurrentWeatherAction()
        registerManager()
        addResponseListener()
        setEvent()
        setAnim()

        getPresenter().getWeather(locationManager.getLocation().latitude, locationManager.getLocation().longitude)
    }

    private fun setAnim() {
        slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
    }

    private fun setEvent() {
        ivWeatherImage.setOnClickListener {
            if (isShow) {
                isShow = false
                llWeather.animation = slideDown
                llWeather.animation.start()
                llWeather.visibility = View.GONE
            } else {
                isShow = true
                llWeather.animation = slideUp
                llWeather.animation.start()
                llWeather.visibility = View.VISIBLE
            }
        }
    }

    override fun createPresenter(): CurrentWeatherPresenter {
        return getAppComponent().getCurrentWeatherComponent().getCurrentWeatherPresenter()
    }

    private fun registerManager() {
        locationManager = getAppComponent().getLocationManager()
        settingManager = getAppComponent().getSettingManager()

        locationManager.addOnLocationChangeListener(this)
        settingManager.addOnSettingChangeListener(this)
    }

    private fun addResponseListener() {
        disposableManager.add(
                action.map(CurrentWeatherAction::weather)
                        .filter { response -> response != null }
                        .subscribe { response ->
                            run {
                                setWeatherData(response.currently)
                                this.currentWeather = response.currently
                            }
                        }
        )

        disposableManager.add(
                action.map(CurrentWeatherAction::errorMessage)
                        .filter { response -> response != null }
                        .subscribe { response ->
                            showToast(response)
                        }
        )
    }

    private fun setWeatherData(currentWeather: CurrentWeather) {
        BitmapHelper.bindWeatherIcon(currentWeather.icon, ivWeatherIcon, ivWeatherImage, context!!)

        tvSumary.text = currentWeather.summary
        tvTemperature.setTemperature(currentWeather.temperature.toDouble())
        wvApparentTemperature.getTvValue().setTemperature(currentWeather.apparentTemperature)

        wvDewPoint.getTvValue().setDegrees(currentWeather.dewPoint.toString())
        wvHumidity.getTvValue().text = currentWeather.humidity.toString()
        wvPressure.getTvValue().setPressure(currentWeather.pressure.toString())
        wvWindGust.getTvValue().setSpeed(currentWeather.windGust.toString())
        wvWindSpeed.getTvValue().setSpeed(currentWeather.windSpeed.toString())
        wvWindBearing.getTvValue().setDegrees(currentWeather.windBearing.toString())
        wvCloudCover.getTvValue().setPercentage(currentWeather.cloudCover.toString())
        wvOzone.getTvValue().setOzone(currentWeather.ozone.toString())
        wvVisibility.getTvValue().setLength(currentWeather.visibility.toString())
    }

    override fun screenResume() {
        ivWeatherImage.start()
    }

    override fun screenPause() {
    }

    override fun screenStart(saveInstanceState: Bundle?) {
    }

    override fun attach(context: Context?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeLocationChangeListener(this)
        settingManager.removeSettingChangeListener(this)
    }
}
