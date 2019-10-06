package com.stdiohue.phamduc.kotlin_viper.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.stdiohue.phamduc.kotlin_viper.R

class WeatherValueView : FrameLayout {
    private lateinit var tvTitle: TextView
    private lateinit var tvValue: TextView
    private lateinit var stTitle: String
    private lateinit var stValue: String

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.widget_weather_value, this, true)

        if (attrs == null) {
            return
        }

        tvTitle = findViewById(R.id.tvTitle)
        tvValue = findViewById(R.id.tvValue)

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.WeatherValueView, 0, 0)

        stTitle = a.getString(R.styleable.WeatherValueView_setTitle) ?: ""
        stValue = a.getString(R.styleable.WeatherValueView_setValue) ?: ""

        if (stTitle != null) {
            tvTitle.text = stTitle
        }
    }

    fun getTvValue(): TextView {
        return tvValue
    }

    fun setValue(value: String) {
        tvValue.text = value
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }
}
