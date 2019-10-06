package com.stdiohue.phamduc.kotlin_viper.base.core

import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.Observable

abstract class BaseActivity : AppCompatActivity() {
    protected fun replaceFragment(view: Int, fragment: Fragment, addToStack: Boolean) {
        if (addToStack) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(view, fragment)
                    .addToBackStack(fragment.javaClass.name)
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(view, fragment)
                    .commit()
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun isInternetOn(): Observable<Boolean> {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val hasNetwork: Boolean
        if (connectivityManager != null) {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            hasNetwork = activeNetworkInfo != null && activeNetworkInfo.isConnected
        } else {
            hasNetwork = false
        }

        return Observable.just(hasNetwork)
    }
}
