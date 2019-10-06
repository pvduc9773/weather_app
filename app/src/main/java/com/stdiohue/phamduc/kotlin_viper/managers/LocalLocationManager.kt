package com.stdiohue.phamduc.kotlin_viper.managers

import android.content.SharedPreferences
import com.google.gson.Gson
import com.stdiohue.phamduc.data.model.UserLocation
import java.util.*

class LocalLocationManager(var sharedPreferences: SharedPreferences, var gson: Gson) : LocationManager {
    override fun refresh() {
        notifyLocationChange(getLocation())
    }

    private var observer = ArrayList<LocationManager.OnLocationChangeListener>()

    override fun storeLocation(location: UserLocation) {
        sharedPreferences.edit().putString("location", gson.toJson(location)).apply()
        notifyLocationChange(location)
    }

    override fun getLocation(): UserLocation {
        var location = gson.fromJson(sharedPreferences.getString("location", ""), UserLocation::class.java)

        if (location != null) {
            return location
        }

        location = UserLocation()
        location.latitude = 16.449222
        location.longitude = 107.584823
        location.title = "Hue"
        return location
    }

    private fun notifyLocationChange(location: UserLocation) {
        if (observer != null && !observer.isEmpty()) {
            for (listener in observer) {
                listener.onLocationChange(location)
            }
        }
    }

    override fun addOnLocationChangeListener(listener: LocationManager.OnLocationChangeListener) {
        observer.add(listener)
    }

    override fun removeLocationChangeListener(listener: LocationManager.OnLocationChangeListener) {
        observer.remove(listener)
    }
}