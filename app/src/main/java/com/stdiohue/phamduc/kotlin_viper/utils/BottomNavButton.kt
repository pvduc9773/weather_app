package com.stdiohue.phamduc.kotlin_viper.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.stdiohue.phamduc.kotlin_viper.R

class BottomNavButton : FrameLayout {
    private var activeColor: Int = 0
    private var deactiveColor: Int = 0
    private var imageActiveColor: Int = 0
    private var imageDeactiveColor: Int = 0
    private val isActive: Boolean = false
    private lateinit var tvTitle: TextView
    private lateinit var ivIcon: ImageView

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.widget_button_nav_bottom, this, true)

        if (attrs == null) {
            return
        }

        activeColor = ContextCompat.getColor(context, R.color.colorWhite)
        deactiveColor = ContextCompat.getColor(context, R.color.colorGreenPadua)

        imageActiveColor = ContextCompat.getColor(context, R.color.colorGreenSpring)
        imageDeactiveColor = ContextCompat.getColor(context, R.color.colorGreenJungle)

        tvTitle = findViewById(R.id.tvTitle)
        ivIcon = findViewById(R.id.ivIcon)

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.BottomNavButton, 0, 0)
        val stTitle: String?
        val iconImage: Int
        try {
            stTitle = a.getString(R.styleable.BottomNavButton_textTitle)
            iconImage = a.getResourceId(R.styleable.BottomNavButton_imageIcon, 0)
//            activeColor = a.getColor(R.styleable.BottomNavButton_activeColor, 0)
//            deactiveColor = a.getColor(R.styleable.BottomNavButton_deActiveColor, 0)
        } finally {
            a.recycle()
        }
    }

    fun updateTitle(content: Int) {
        tvTitle.setText(content)
    }

    fun active() {
        tvTitle.setTextColor(activeColor)
        ivIcon.setBackgroundColor(imageActiveColor)
    }

    fun deActive() {
        tvTitle.setTextColor(deactiveColor)
        ivIcon.setBackgroundColor(imageDeactiveColor)
    }
}
