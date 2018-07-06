package id.pahlevikun.easygroupmaker.presenter.implementation.saved

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable
import id.pahlevikun.easygroupmaker.presenter.interfaces.callback.ItemTouchCallback
import id.pahlevikun.easygroupmaker.presenter.interfaces.saved.UserGroupInterface
import id.pahlevikun.easygroupmaker.view.adapter.SavedUserAdapter

class UserGroupPresenter : UserGroupInterface {
    override fun gettingData(context: Context): List<UserGroupTable> {
        val data = RoomInitializer.initDatabase(context).userGroupDaoAccess().selectAllUserGroup()
        RoomInitializer.destroyGroupList()
        return data
    }

    override fun parsingBackToArray(value: String): Array<String> {
        val joinedMinusBrackets = value.substring(1, value.length - 1)
        val resplit = joinedMinusBrackets.split(", ").toTypedArray()
        return resplit
    }

    override fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: List<UserGroupTable>, callback: ItemTouchCallback): SavedUserAdapter {
        val adapter = SavedUserAdapter(arrayList, object : ItemTouchCallback {
            override fun onItemTouch(data: Array<String>) {
                callback.onItemTouch(data)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return adapter
    }
}