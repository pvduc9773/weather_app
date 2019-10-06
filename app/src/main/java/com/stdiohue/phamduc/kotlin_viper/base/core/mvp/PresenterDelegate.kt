package com.stdiohue.phamduc.kotlin_viper.base.core.mvp

import android.content.Intent

class PresenterDelegate(var presenter: Presenter) : Presenter {
    override fun attach(view: Presenter.View) {
        presenter.attach(view)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun detach() {
        presenter.detach()
    }

    override fun resume() {
        presenter.resume()
    }

    override fun pause() {
        presenter.pause()
    }

    override fun init() {
        presenter.init()
    }

    override fun destroy() {
        presenter.destroy()
    }
}
