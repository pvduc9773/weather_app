package com.stdiohue.phamduc.kotlin_viper.base.core.mvp

import android.content.Intent

interface Presenter {
    interface View

    fun attach(view: View)

    fun detach()

    fun resume()

    fun pause()

    fun init()

    fun destroy()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
}
