package com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.stdiohue.phamduc.data.model.Setting
import com.stdiohue.phamduc.data.model.UserLocation
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppFragment
import com.stdiohue.phamduc.kotlin_viper.managers.LocationManager
import com.stdiohue.phamduc.kotlin_viper.managers.SettingManager
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.HourlyWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.HourlyWeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.hourlyweather.ui.adapter.HourlyWeatherAdapter
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_hourly_weather.*

class HourlyWeatherFragment : BaseAppFragment<HourlyWeatherPresenter>(), LocationManager.OnLocationChangeListener, SettingManager.OnSettingChangeListener {
    override fun onSettingChange(setting: Setting) {
        adapter.notifyDataSetChanged()
    }

    private lateinit var action: PublishSubject<HourlyWeatherAction>
    private lateinit var locationManager: LocationManager
    private lateinit var adapter: HourlyWeatherAdapter
    private lateinit var settingManager: SettingManager

    override fun onLocationChange(newLocation: UserLocation) {
        getPresenter().getWeather(newLocation.latitude, newLocation.longitude)
    }

    companion object {
        fun newInstance() = HourlyWeatherFragment().apply { }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_hourly_weather
    }

    override fun init(view: View?) {
        action = getAppComponent().getHourlyWeatherComponent().getHourlyWeatherAction()
        adapter = HourlyWeatherAdapter()
        rvHourlyWeather.layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
        rvHourlyWeather.adapter = adapter
        rvHourlyWeather.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))

        registerManager()
        addResponseListener()

        getPresenter().getWeather(locationManager.getLocation().latitude, locationManager.getLocation().longitude)
    }

    override fun createPresenter(): HourlyWeatherPresenter {
        return getAppComponent().getHourlyWeatherComponent().getHourlyWeatherPresenter()
    }

    private fun registerManager() {
        locationManager = getAppComponent().getLocationManager()
        settingManager = getAppComponent().getSettingManager()

        locationManager.addOnLocationChangeListener(this)
        settingManager.addOnSettingChangeListener(this)
    }

    private fun addResponseListener() {
        disposableManager.add(
                action.map(HourlyWeatherAction::weather)
                        .filter { response -> response != null }
                        .subscribe { response ->
                            adapter.updateAdapter(response.hourly.data)
                        }
        )

        disposableManager.add(
                action.map(HourlyWeatherAction::errorMessage)
                        .filter { response -> response != null }
                        .subscribe { response ->
                            showToast(response)
                        }
        )
    }

    override fun screenResume() {
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
