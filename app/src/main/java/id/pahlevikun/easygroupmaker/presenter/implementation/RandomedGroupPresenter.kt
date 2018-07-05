package id.pahlevikun.easygroupmaker.presenter.implementation

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable
import id.pahlevikun.easygroupmaker.presenter.`interface`.ItemTouchCallback
import id.pahlevikun.easygroupmaker.presenter.`interface`.RandomedGroupInterface
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAdapter

class RandomedGroupPresenter : RandomedGroupInterface {
    override fun gettingData(context: Context): List<UserGroupTable> {
        val data = RoomInitializer.initDatabase(context).userGroupDaoAccess().selectAllUserGroup()
        RoomInitializer.destroyGroupList()
        return data
    }

    override fun parsingBackToArray(value: String): Array<String> {
        val joinedMinusBrackets = value.substring(1, value.length - 1)
        Log.d("HASIL", "AFTER SUBSTRING : $joinedMinusBrackets")
        val resplit = joinedMinusBrackets.split(", ").toTypedArray()
        return resplit
    }

    override fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: List<UserGroupTable>, callback: ItemTouchCallback): NewGroupAdapter {
        val adapter = NewGroupAdapter(arrayList, object : ItemTouchCallback {
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