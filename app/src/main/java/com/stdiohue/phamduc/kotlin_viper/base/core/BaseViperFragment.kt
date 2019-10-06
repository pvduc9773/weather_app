package com.stdiohue.phamduc.kotlin_viper.base.core

import android.os.Bundle
import android.view.View
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.PresenterDelegate
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins

abstract class BaseViperFragment<P : Presenter> : BaseFragment(), Presenter.View {
    protected abstract fun createPresenter(): P

    private lateinit var presenter: P

    protected fun getPresenter(): P {
        return presenter
    }

    private var presenterDelegate: PresenterDelegate? = null
    protected lateinit var disposableManager: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposableManager = CompositeDisposable()
        presenter = createPresenter()
        presenterDelegate = PresenterDelegate(presenter)
        presenterDelegate!!.attach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenterDelegate!!.init()
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }

    override fun onResume() {
        super.onResume()
        presenterDelegate!!.resume()
    }

    override fun onPause() {
        super.onPause()
        presenterDelegate!!.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableManager.clear()
        presenterDelegate!!.detach()
        presenterDelegate!!.destroy()
    }
}
