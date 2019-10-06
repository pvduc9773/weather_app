package com.stdiohue.phamduc.kotlin_viper.module.setting

import io.reactivex.subjects.PublishSubject


class SettingAction {
    companion object {
        var publisher: PublishSubject<SettingAction> = PublishSubject.create()

        fun isLoading(isLoading: Boolean): SettingAction {
            val action = SettingAction()
            action.isLoading = isLoading
            return action
        }

        fun error(mess: String): SettingAction {
            val action = SettingAction()
            action.errorMessage = mess
            return action
        }
    }

    var isLoading: Boolean = false
    var errorMessage: String? = null
}