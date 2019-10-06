package com.stdiohue.phamduc.kotlin_viper.utils

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AlertDialog
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

class RxDialog {
    enum class ChooseType {
        CANCEL, PLAY, CONVERT
    }

    fun confirmDialog(context: Context, title: String, message: String): Observable<Boolean> {
        return Observable.create(ConfirmDialogObservable(context, title, message))
    }

    fun notiDialog(context: Context, title: String, message: String): Observable<Boolean> {
        return Observable.create(NotiDialogObservable(context, title, message))
    }

    internal class ConfirmDialogObservable(private val context: Context, private val title: String, private val message: String) : ObservableOnSubscribe<Boolean> {
        private var dialog: Dialog? = null

        override fun subscribe(emitter: ObservableEmitter<Boolean>) {
            if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
            dialog = AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                        emitter.onNext(true)
                    }
                    .setNegativeButton("Cancel") { dialog, which ->
                        dialog.dismiss()
                        emitter.onNext(false)
                    }
                    .create()
            dialog!!.show()
        }
    }

    internal class NotiDialogObservable(private val context: Context, private val title: String, private val message: String) : ObservableOnSubscribe<Boolean> {
        private var dialog: Dialog? = null

        override fun subscribe(emitter: ObservableEmitter<Boolean>) {
            if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
            dialog = AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                        emitter.onNext(true)
                    }
                    .setCancelable(false)
                    .create()
            dialog!!.show()
        }
    }
}