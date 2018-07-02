package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.content.Context

interface MainInterface {

    fun getSumOfGroupSize(context: Context): Int

    fun getSumOfRandomNumber(context: Context): Int

    fun countRandom(context: Context)

    fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean
}