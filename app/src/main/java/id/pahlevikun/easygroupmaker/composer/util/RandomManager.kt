/*
 * SessionManager Created with ♥ by PT. Deggan Technowave on 1/8/18 11:36 AM
 * PT. Deggan Technowave Copyright (c) 2018. All Rights Reserved.
 * Last Modified 1/8/18 10:59 AM
 */

package id.pahlevikun.easygroupmaker.composer.util

import android.app.Activity
import android.content.SharedPreferences
import id.pahlevikun.easygroupmaker.R

/**
 * Created by farhan on 1/8/18.
 */

class RandomManager(private val context: Activity) {

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val PRIVATE_MODE = 0

    val currentRandomed: Int
        get() = pref.getInt(context.getString(R.string.randomedValue), 0)

    init {
        pref = context.getSharedPreferences(context.getString(R.string.sharedRandomed), PRIVATE_MODE)
        editor = pref.edit()
    }

    fun addRandomed(randomedValue: Int) {
        val storeValue = randomedValue + 1
        editor.putInt(context.getString(R.string.randomedValue), storeValue)
        editor.commit()
    }

    fun clearRandomed() {
        editor.clear()
        editor.commit()
    }
}