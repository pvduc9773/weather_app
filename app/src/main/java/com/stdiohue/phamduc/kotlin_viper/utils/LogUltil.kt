package com.stdiohue.phamduc.kotlin_viper.utils

import android.util.Log

class LogUltil {
    companion object {
        private val LOG_STATUS = true

        fun log(className: Class<*>, log: Any) {
            if (LOG_STATUS) {
                Log.e("THIEN", className.simpleName + ": " + log.toString())
            }
        }
    }
}
