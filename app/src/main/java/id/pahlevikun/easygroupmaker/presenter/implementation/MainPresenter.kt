package id.pahlevikun.easygroupmaker.presenter.implementation

import android.content.Context
import id.pahlevikun.easygroupmaker.composer.util.RandomManager
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.presenter.interfaces.MainInterface

class MainPresenter : MainInterface {

    override fun countRandom(context: Context) {
        RandomManager(context).addRandomed()
    }

    override fun getSumOfGroupSize(context: Context): Int {
        val dataCount = RoomInitializer.initDatabase(context).randomGroupListDaoAccess().countData()
        return if (dataCount > 100) {
            dataCount / 100
        } else {
            dataCount
        }
    }

    override fun getSumOfRandomNumber(context: Context): Int {
        val randomManager = RandomManager(context)
        val dataCount = randomManager.currentRandomed
        return if (dataCount > 100) {
            randomManager.clearRandomed()
            randomManager.currentRandomed
        } else {
            dataCount
        }
    }

    override fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean {
        return !sumOfGroup.isEmpty() || !sumOfPerson.isEmpty()
    }
}