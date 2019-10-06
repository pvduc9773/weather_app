package com.stdiohue.phamduc.kotlin_viper.utils

import android.content.Context
import android.widget.ImageView
import android.widget.VideoView
import com.bumptech.glide.Glide
import com.stdiohue.phamduc.kotlin_viper.R

class BitmapHelper {
    companion object {
        fun bindImage(v: ImageView?, imageId: Int) {
            if (v != null) {
                Glide.with(v.context)
                        .load(imageId)
                        .into(v)
            }
        }

        fun bindWeatherIcon(iconName: String, icon: ImageView?, image: VideoView?, context: Context) {
            when (iconName) {
                "clear-day" -> {
                    bindImage(icon, R.drawable.ic_clear_day)
                    VideoHelper.playVideo(image, R.raw.clear_day_video, context)
                }
                "clear-night" -> {
                    bindImage(icon, R.drawable.ic_clear_night)
                    VideoHelper.playVideo(image, R.raw.clear_night_video, context)
                }
                "rain" -> {
                    bindImage(icon, R.drawable.ic_rain)
                    VideoHelper.playVideo(image, R.raw.rain_video, context)
                }
                "snow" -> {
                    bindImage(icon, R.drawable.ic_snow)
                    VideoHelper.playVideo(image, R.raw.snow_video, context)
                }
                "sleet" -> {
                    bindImage(icon, R.drawable.ic_sleet)
                    VideoHelper.playVideo(image, R.raw.cloudy_video, context)
                }
                "wind" -> {
                    bindImage(icon, R.drawable.ic_wind)
                    VideoHelper.playVideo(image, R.raw.wind_video, context)
                }
                "fog" -> {
                    bindImage(icon, R.drawable.ic_fog)
                    VideoHelper.playVideo(image, R.raw.fog_video, context)
                }
                "cloudy" -> {
                    bindImage(icon, R.drawable.ic_cloudy)
                    VideoHelper.playVideo(image, R.raw.cloudy_video, context)
                }
                "partly-cloudy-day" -> {
                    bindImage(icon, R.drawable.ic_partly_cloudy_day)
                    VideoHelper.playVideo(image, R.raw.partly_cloudy_day_video, context)
                }
                "partly-cloudy-night" -> {
                    bindImage(icon, R.drawable.ic_partly_cloudy_night)
                    VideoHelper.playVideo(image, R.raw.partly_cloudy_night_video, context)
                }
                "thunderstorm" -> {
                    bindImage(icon, R.drawable.ic_partly_cloudy_night)
                    VideoHelper.playVideo(image, R.raw.thunderstorm_video, context)
                }
            }
        }
    }
}