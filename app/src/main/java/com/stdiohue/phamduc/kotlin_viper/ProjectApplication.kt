package com.stdiohue.phamduc.kotlin_viper

import android.support.multidex.MultiDexApplication
import com.stdiohue.phamduc.kotlin_viper.di.components.AppComponent
import com.stdiohue.phamduc.kotlin_viper.di.components.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins

class ProjectApplication : MultiDexApplication() {
    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        getAppComponent().inject(this)
        RxJavaPlugins.setErrorHandler { error -> error.printStackTrace() }
    }

    fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .context(this)
                    .build()
        }
        return appComponent!!
    }
}