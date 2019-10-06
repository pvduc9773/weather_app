package com.stdiohue.phamduc.kotlin_viper.managers

import com.stdiohue.phamduc.data.model.UserLocation

interface LocationManager {
    interface OnLocationChangeListener {
        fun onLocationChange(newLocation: UserLocation)
    }

    fun addOnLocationChangeListener(listener: OnLocationChangeListener)

    fun removeLocationChangeListener(listener: OnLocationChangeListener)

    fun storeLocation(location: UserLocation)

    fun getLocation(): UserLocation

    fun refresh()
}