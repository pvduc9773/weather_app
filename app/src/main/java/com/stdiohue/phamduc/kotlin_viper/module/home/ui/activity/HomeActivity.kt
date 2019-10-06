package com.stdiohue.phamduc.kotlin_viper.module.home.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.location.places.ui.PlacePicker
import com.stdiohue.phamduc.data.model.UserLocation
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppActivity
import com.stdiohue.phamduc.kotlin_viper.managers.LocationManager
import com.stdiohue.phamduc.kotlin_viper.managers.SettingManager
import com.stdiohue.phamduc.kotlin_viper.module.home.WeatherAction
import com.stdiohue.phamduc.kotlin_viper.module.home.presenter.WeatherPresenter
import com.stdiohue.phamduc.kotlin_viper.module.home.ui.adapter.HomePagerAdapter
import com.stdiohue.phamduc.kotlin_viper.module.setting.ui.activity.WeatherAnimationActivity
import com.stdiohue.phamduc.kotlin_viper.utils.RxDialog
import com.stdiohue.phamduc.kotlin_viper.utils.showLogIfNeed
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseAppActivity<WeatherPresenter>() {
    private lateinit var action: PublishSubject<WeatherAction>
    private var PLACE_AUTOCOMPLETE_REQUEST_CODE: Int = 99
    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var locationManager: LocationManager
    private lateinit var settingManager: SettingManager

    companion object {
        fun startActivity(context: Context) {
            var intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun init() {
        locationManager = getAppComponent().getLocationManager()
        action = getAppComponent().getWeatherComponent().getWeatherAction()
        homePagerAdapter = HomePagerAdapter(supportFragmentManager)
        settingManager = getAppComponent().getSettingManager()

        if (settingManager.getSetting().temperature) {
            swTemperature.isChecked = true
        }

        vpHome.adapter = homePagerAdapter
        tvLoacation.text = locationManager.getLocation().title

        updateBottomBar(0)

        vpHome.offscreenPageLimit = 3
            vpHome.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    updateBottomBar(position)
                }

            //remove pull of SwipeRefreshLayout when viewpager scrolling
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                slHome.isEnabled = state == ViewPager.SCROLL_STATE_IDLE
            }
        })
        menu.bringToFront()
        setTitleButton()
        setEvent()

        showLogIfNeed(vpHome.adapter != null) {
            print("co adapter")
        }

        initSpinner()
    }

    private fun initSpinner() {
        ArrayAdapter
                .createFromResource(this, R.array.language_list, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    snChangeLanguage.adapter = adapter
                }
    }

    private fun setEvent() {
        tvLoacation.setOnClickListener {
            chooseLocation()
        }

        ivMenu.setOnClickListener {
            changeSettingView()
        }

        bnCurrently.setOnClickListener { updateBottomBar(0) }
        bnHourly.setOnClickListener { updateBottomBar(1) }
        bnDaily.setOnClickListener { updateBottomBar(2) }

        tvViewAnimation.setOnClickListener { WeatherAnimationActivity.startActivity(this) }
        tvChangeLocation.setOnClickListener {
            chooseLocation()
            changeSettingView()
        }

        tvCopyRight.setOnClickListener {
            RxDialog()
                    .notiDialog(this, getString(R.string.copyright), getString(R.string.copyright_mess))
                    .subscribe()
        }

        swTemperature.setOnCheckedChangeListener { _, b ->
            settingManager.storeTemperatureSetting(b)
        }

        slHome.setOnRefreshListener {
            locationManager.refresh()
            slHome.isRefreshing = false
        }

        snChangeLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    setLanguage("en")
                } else {
                    setLanguage("vi")
                }
            }
        }
    }

    private fun changeSettingView() {
        if (dlWeather.isDrawerOpen(Gravity.RIGHT)) {
            dlWeather.closeDrawers()
        } else {
            dlWeather.openDrawer(Gravity.RIGHT)
        }
    }

    private fun chooseLocation() {
        try {
            var builder: PlacePicker.IntentBuilder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this), PLACE_AUTOCOMPLETE_REQUEST_CODE)
        } catch (e: GooglePlayServicesRepairableException) {
        } catch (e: GooglePlayServicesNotAvailableException) {
        }
    }

    private fun setTitleButton() {
        bnCurrently.updateTitle(R.string.current)
        bnHourly.updateTitle(R.string.hourly)
        bnDaily.updateTitle(R.string.daily)
    }

    private fun updateBottomBar(activePosition: Int) {
        bnCurrently.deActive()
        bnHourly.deActive()
        bnDaily.deActive()
        when (activePosition) {
            0 -> bnCurrently.active()
            1 -> bnHourly.active()
            2 -> bnDaily.active()
        }
        vpHome.setCurrentItem(activePosition, true)
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
                var place: Place = PlaceAutocomplete.getPlace(this, data)

                if (TextUtils.isEmpty(place.address.toString())) {
                    showToast(getString(R.string.we_cant_get_your_location_please_choose_it_again))
                } else {
                    tvLoacation.text = place.address.toString()

                    var userLocation = UserLocation()
                    userLocation.latitude = place.latLng.latitude
                    userLocation.longitude = place.latLng.longitude
                    userLocation.title = place.address.toString()
                    locationManager.storeLocation(userLocation)
                }
            }
        }

    override fun startScreen() {
    }

    override fun resumeScreen() {
    }

    override fun pauseScreen() {
    }

    override fun destroyScreen() {
    }

    override fun createPresenter(): WeatherPresenter {
        return getAppComponent().getWeatherComponent().getWeatherPresenter()
    }
}