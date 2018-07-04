package id.pahlevikun.easygroupmaker.presenter.implementation

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.presenter.`interface`.PreferencesInterface
import id.pahlevikun.easygroupmaker.view.ui.MainActivity
import java.util.*

class PreferencesPresenter : PreferencesInterface {
    override fun deleteUserListGroup(context: Context) {
        RoomInitializer.initDatabase(context).userGroupDaoAccess().deleteAllUserGroup()
        RoomInitializer.destroyGroupList()
    }

    override fun deleteRandomedGroup(context: Context) {
        RoomInitializer.initDatabase(context).groupListDaoAccess().deleteAllGroupList()
        RoomInitializer.destroyGroupList()
    }

    override fun deleteAllData(context: Context) {
        RoomInitializer.initDatabase(context).userGroupDaoAccess().deleteAllUserGroup()
        RoomInitializer.initDatabase(context).groupListDaoAccess().deleteAllGroupList()
        RoomInitializer.destroyGroupList()
    }

    override fun changeLanguage(context: Activity, langval:String) {
        val config = context.resources.configuration
        val locale = Locale(langval)
        Locale.setDefault(locale)
        config.locale = locale
        context.resources.updateConfiguration(config,context.resources.displayMetrics)

        val intent = Intent(context,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        context.finish()
    }

}