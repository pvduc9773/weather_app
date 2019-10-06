package com.stdiohue.phamduc.kotlin_viper.base.core.mvp

import android.content.Intent
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter : Presenter {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    private var view: Presenter.View? = null
    protected lateinit var disposable: CompositeDisposable

    fun getView(): Presenter.View? {
        return view
    }

    override fun attach(view: Presenter.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun init() {
        disposable = CompositeDisposable()
    }

    override fun destroy() {
        disposable.clear()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

    }
}
