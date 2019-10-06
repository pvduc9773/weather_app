package com.stdiohue.phamduc.kotlin_viper.base.core

import android.content.Intent
import android.os.Bundle
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.PresenterDelegate
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViperActivity<P : Presenter> : BaseActivity(), Presenter.View {
    protected abstract fun createPresenter(): P
    private lateinit var presenter: P

    protected fun getPresenter(): P {
        return presenter
    }

    private lateinit var presenterDelegate: PresenterDelegate

    protected lateinit var disposableManager: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposableManager = CompositeDisposable()
        presenterDelegate = PresenterDelegate(presenter = createPresenter())
        presenterDelegate.attach(this)
        presenterDelegate.init()
    }

    override fun onResume() {
        super.onResume()
        presenterDelegate.resume()
    }

    override fun onPause() {
        super.onPause()
        presenterDelegate.pause()
    }

    override fun onDestroy() {
        disposableManager.clear()
        presenterDelegate.detach()
        presenterDelegate.destroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenterDelegate.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenterDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

