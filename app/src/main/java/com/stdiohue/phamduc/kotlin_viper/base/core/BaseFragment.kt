package com.stdiohue.phamduc.kotlin_viper.base.core

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.widget.Toast
import io.reactivex.Observable

open class BaseFragment : Fragment() {
    protected fun replaceFragment(@IdRes frameId: Int, fragment: Fragment, addToBackTrack: Boolean) {
        val transaction = childFragmentManager.beginTransaction()
        if (addToBackTrack) {
            transaction.addToBackStack(this.javaClass.simpleName)
        }
        transaction.replace(frameId, fragment)
                .commitAllowingStateLoss()
    }

    protected fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun isInternetOn(): Observable<Boolean> {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
