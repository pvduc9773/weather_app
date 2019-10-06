package com.stdiohue.phamduc.kotlin_viper.module.splash.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.provider.Settings
import com.stdiohue.phamduc.kotlin_viper.R
import com.stdiohue.phamduc.kotlin_viper.base.core.BaseAppActivity
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.BasePresenter
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.module.home.ui.activity.HomeActivity
import com.stdiohue.phamduc.kotlin_viper.utils.RxDialog
import com.stdiohue.phamduc.kotlin_viper.utils.VideoHelper
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseAppActivity<Presenter>() {
    private lateinit var rxPermissions: RxPermissions

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        VideoHelper.playVideo(ivSplash, R.raw.sad_video, this)
        rxPermissions = RxPermissions(this)
        disposableManager.add(rxPermissions
                .request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { hasPermission ->
                    if (!hasPermission) {
                        disposableManager.add(
                                RxDialog().confirmDialog(this, getString(R.string.permission), getString(R.string.please_add_requites_permission_to_use_app))
                                        .subscribe { respone ->
                                            if (respone) {
                                                val intent = Intent()
                                                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                                val uri = Uri.fromParts("package", packageName, null)
                                                intent.data = uri
                                                startActivity(intent)
                                            } else {
                                                finish()
                                            }
                                        })
                    } else {
                        Handler().postDelayed(
                                { HomeActivity.startActivity(this) }, 3000)
                    }
                })
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