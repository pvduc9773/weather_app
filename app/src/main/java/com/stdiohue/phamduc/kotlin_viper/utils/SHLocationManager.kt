package com.stdiohue.phamduc.kotlin_viper.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.LocationServices

class SHLocationManager {
    val REQUEST_CODE = 1991
    val PERMISSION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    fun checkPermission(context: Context): Boolean {
        if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(context)
                        .setTitle("Request location permission!")
                        .setMessage("Grant location permission to use this feature!!!")
                        .setPositiveButton("OK") { _, _ ->
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                    REQUEST_CODE)
                        }
                        .create()
                        .show()
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE)
            }
            return false
        } else {
            return true
        }
    }

    fun getLastKnowLocation(context: Context, callback: OnCurrentLocationCallback) {
        if (checkPermission(context)) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var getLastLocation: Location? = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)

            if (getLastLocation != null) {
                callback.callback(getLastLocation)
                return
            }
            getLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (getLastLocation != null) {
                callback.callback(getLastLocation)
                return
            }
            getLastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (getLastLocation != null) {
                callback.callback(getLastLocation)
                return
            }

            listenerLastLocation(context, callback)
        }
    }

    fun listenerLastLocation(context: Context, callback: OnCurrentLocationCallback) {
        if (checkPermission(context))
            LocationServices.getFusedLocationProviderClient(context)
                    .lastLocation
                    .addOnSuccessListener { callback.callback(it) }
    }

    fun checkLocationEnable(
            activity: Activity, listener: CheckLocationEnableListener) {
        val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder(activity)
                    .setTitle("Enable location sensor!")
                    .setCancelable(false)
                    .setMessage("Enable location for best search result!!!")
                    .setPositiveButton("Yes") { dialog, which ->
                        val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        activity.startActivity(i)

                        listener.onNotEnable()
                    }
                    .setNegativeButton("No") { dialog, which -> listener.onNotEnable() }
                    .create().show()
        }

        listener.onEnabled()
    }

    interface CheckLocationEnableListener {
        fun onNotEnable()

        fun onEnabled()
    }

    interface OnCurrentLocationCallback {
        fun callback(location: Location)
    }
}