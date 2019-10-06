package com.stdiohue.phamduc.kotlin_viper.utils

import android.content.Context
import android.net.Uri
import android.widget.VideoView

class VideoHelper {
    companion object {
        fun playVideo(v: VideoView?, videoId: Int, context: Context) {
            if (v != null) {
                val uri = Uri.parse("android.resource://" + context?.packageName + "/raw/" + videoId)
                v.setVideoURI(uri)
                v.setOnPreparedListener { mp -> mp.isLooping = true }
                v.start()
            }
        }
    }
}