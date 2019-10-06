package com.stdiohue.phamduc.kotlin_viper.base.core

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.stdiohue.phamduc.kotlin_viper.ProjectApplication
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.di.components.AppComponent

abstract class BaseAppActivity<P : Presenter> : BaseViperActivity<P>() {
    private val localizationDelegate = LocalizationActivityDelegate(this)

    protected fun getAppComponent(): AppComponent {
        return (application as ProjectApplication).getAppComponent()
    }

    /**
     * setup content layout
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * init for data
     */
    protected abstract fun init()

    /**
     * start screen
     */
    protected abstract fun startScreen()

    /**
     * resume screen
     */
    protected abstract fun resumeScreen()

    /**
     * pause screen
     */
    protected abstract fun pauseScreen()

    /**
     * destroy screen
     */
    protected abstract fun destroyScreen()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        localizationDelegate.onCreate(savedInstanceState)
        init()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(localizationDelegate.attachBaseContext(newBase))
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    fun setLanguage(language: String) {
        localizationDelegate.setLanguage(this, language)
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(super.getResources())
    }

    override fun onStart() {
        super.onStart()
        startScreen()
    }

    override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
        resumeScreen()
    }

    override fun onPause() {
        super.onPause()
        pauseScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyScreen()
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val v = currentFocus
        if (v is EditText) {
            val scoops = IntArray(2)
            v.getLocationOnScreen(scoops)
            val x = event.rawX + v.left - scoops[0]
            val y = event.rawY + v.top - scoops[1]

            if (event.action == MotionEvent.ACTION_UP && (x < v.left || x >= v.right || y < v.top || y > v
                            .bottom)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(v
                        .windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(event)
    }

    /**
     * Showing a quick little message for the user. It's will be cancel when
     * finish activity.
     */
    private var mToast: Toast? = null


    /**
     * Show message by the Toast object, it will be canceled when finish this
     * activity.
     *
     * @param msg message want to show
     */
    fun showToastMessage(msg: CharSequence) {
        if (isFinishing)
            return
        if (mToast == null) {
            mToast = Toast.makeText(applicationContext, "",
                    Toast.LENGTH_LONG)
        }

        if (mToast != null) {
            // Cancel last message toast
            if (mToast!!.view.isShown) {
                mToast!!.cancel()
            }
            mToast!!.setText(msg)
            mToast!!.show()
        }
    }

    /**
     * Show message by the Toast object, it will be canceled when finish this
     * activity.
     *
     * @param resId id of message wants to show
     */
    fun showToastMessage(@StringRes resId: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(applicationContext, "",
                    Toast.LENGTH_LONG)
        }

        if (mToast != null) {
            if (mToast!!.view.isShown) {
                mToast!!.cancel()
            }
            mToast!!.setText(getString(resId))
            mToast!!.show()
        }
    }

    /**
     * Cancel toast.
     */
    fun cancelToast() {
        if (mToast != null && mToast!!.view.isShown) {
            mToast!!.cancel()
        }
    }
}