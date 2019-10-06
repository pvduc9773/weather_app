package com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.stdiohue.phamduc.data.model.DailyWeatherData
import com.stdiohue.phamduc.data.model.Setting
import com.stdiohue.phamduc.data.model.UserLocation
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppFragment
import com.stdiohue.phamduc.kotlin_viper.managers.LocationManager
import com.stdiohue.phamduc.kotlin_viper.managers.SettingManager
import com.stdiohue.phamduc.kotlin_viper.module.currentweather.presenter.DailyWeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.DailyWeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.activity.DailyWeatherDetailActivity
import com.stdiohue.phamduc.kotlin_viper.module.dailyweather.ui.adapter.DailyWeatherAdapter
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_daily_weather.*

class DailyWeatherFragment : BaseAppFragment<DailyWeatherPresenter>(), LocationManager.OnLocationChangeListener, SettingManager.OnSettingChangeListener,
        DailyWeatherAdapter.ItemDailyWeatherListener {
    override fun onSettingChange(setting: Setting) {
        adapter.notifyDataSetChanged()
    }

    override fun onLocationChange(newLocation: UserLocation) {
        getPresenter().getWeather(newLocation.latitude, newLocation.longitude)
    }

    private lateinit var action: PublishSubject<DailyWeatherAction>
    private lateinit var adapter: DailyWeatherAdapter
    private lateinit var locationManager: LocationManager
    private lateinit var settingManager: SettingManager

    companion object {
        @JvmStatic
        fun newInstance() = DailyWeatherFragment().apply {}
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_daily_weather
    }

    override fun createPresenter(): DailyWeatherPresenter {
        return getAppComponent().getDailyWeatherComponent().getDailyWeatherPresenter()
    }

    override fun init(view: View?) {
        action = getAppComponent().getDailyWeatherComponent().getDailyWeatherAction()
        adapter = DailyWeatherAdapter(this)

        registerManager()
        addResponseListener()

        rvDailyWeather.adapter = adapter
        rvDailyWeather.layoutManager = LinearLayoutManager(context)
        rvDailyWeather.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))

        getPresenter().getWeather(locationManager.getLocation().latitude, locationManager.getLocation().longitude)
    }

    private fun registerManager() {
        locationManager = getAppComponent().getLocationManager()
        settingManager = getAppComponent().getSettingManager()

        locationManager.addOnLocationChangeListener(this)
        settingManager.addOnSettingChangeListener(this)
    }

    private fun addResponseListener() {
        disposableManager.add(
                action.map(DailyWeatherAction::weather)
                        .filter { response -> response != null }
                        .subscribe { response ->
                            adapter.updateAdapter(response.daily.data)
                        }
        )

        disposableManager.add(
                action.map(DailyWeatherAction::errorMessage)
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

    override fun onItemWeatherClick(weather: DailyWeatherData) {
        DailyWeatherDetailActivity.startActivity(context!!, weather)
    }
}
