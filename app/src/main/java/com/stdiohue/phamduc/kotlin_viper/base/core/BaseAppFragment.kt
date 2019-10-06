package com.stdiohue.phamduc.kotlin_viper.base.core

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.stdiohue.phamduc.kotlin_viper.ProjectApplication
import com.stdiohue.phamduc.kotlin_viper.base.core.mvp.Presenter
import com.stdiohue.phamduc.kotlin_viper.di.components.AppComponent

abstract class BaseAppFragment<P : Presenter> : BaseViperFragment<P>() {
    private var isAttach: Boolean = false

    fun isAttach(): Boolean {
        return isAttach
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun init(view: View?)

    protected abstract fun screenResume()

    protected abstract fun screenPause()

    protected abstract fun screenStart(saveInstanceState: Bundle?)

    protected fun getAppComponent(): AppComponent {
        return (getActivity()!!.getApplication() as ProjectApplication).getAppComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isAttach = false
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        screenStart(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    override fun onResume() {
        super.onResume()
        screenResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
    }

    override fun onPause() {
        super.onPause()
        screenPause()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        isAttach = true
        attach(context)
    }

    protected abstract fun attach(context: Context?)

    private var mToast: Toast? = null

    fun showToastMessage(@StringRes resId: Int) {
        if (!isAdded())
            return
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), "",
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

    /**
     * Show toast message, the message is not canceled when finish this
     * activity.
     *
     * @param msg message wants to show
     */
    fun showToastMessageNotRelease(msg: CharSequence) {
        val toast = Toast.makeText(getActivity(), "",
                Toast.LENGTH_LONG)
        if (toast != null) {
            // Cancel last message toast
            if (toast.view.isShown) {
                toast.cancel()
            }
            toast.setText(msg)
            toast.show()
        }
    }

    /**
     * Show toast message, the message is not canceled when finish this
     * activity.
     *
     * @param resId message wants to show
     */

    fun showToastMessageNotRelease(@StringRes resId: Int) {
        val toast = Toast.makeText(getActivity(), "",
                Toast.LENGTH_LONG)
        if (toast != null) {
            if (!toast.view.isShown) {
                toast.cancel()
            }
            toast.setText(getString(resId))
            toast.show()
        }
    }
}