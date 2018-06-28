package id.pahlevikun.easygroupmaker.presenter.implementation

import android.content.Context
import id.pahlevikun.easygroupmaker.presenter.`interface`.MainInterface

class MainPresenter : MainInterface {
    override fun getSumOfGroupSize(context: Context): Int {
        return 0
    }

    override fun getSumOfRandomNumber(context: Context): Int {
        return 0
    }

    override fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean {
        return !sumOfGroup.isEmpty() || !sumOfPerson.isEmpty()
    }
}