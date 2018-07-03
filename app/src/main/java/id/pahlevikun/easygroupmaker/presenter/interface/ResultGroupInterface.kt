package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.content.Context

interface ResultGroupInterface {

    fun isFieldEmpty(name: String, desc: String): Boolean

    fun countRandom(context: Context)

    fun roundHalfToUp(value: Double): Int

    fun beginRandomize(sumOfPerson: Array<String>, sumOfGroup: String, isSizeMethod: Boolean): Array<Array<String>>

    fun saveToDatabase(context: Context, name: String, description: String, savedValue: Array<Array<String>>)

    fun parseArrayToHumanReadable(value: Array<Array<String>>): String
}