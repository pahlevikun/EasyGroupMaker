package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.content.Context

interface QuickInterface {
    fun roundHalfToUp(value: Double): Int

    fun beginRandomize(sumOfPerson: String, sumOfGroup: String, isSizeMethod: Boolean): Array<IntArray>

    fun saveToDatabase(context: Context, name: String, description: String, savedValue: Array<IntArray>)

    fun parseArrayToHumanReadable(value: Array<IntArray>): String
}