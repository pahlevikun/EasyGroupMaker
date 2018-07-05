package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.app.Activity
import android.content.Context

interface PreferencesInterface {
    fun deleteUserListGroup(context: Context)

    fun deleteRandomedGroup(context: Context)

    fun deleteAllData(context: Context)

    fun changeLanguage(context: Activity, languageValue: String)

    fun factoryReset(context: Context)
}