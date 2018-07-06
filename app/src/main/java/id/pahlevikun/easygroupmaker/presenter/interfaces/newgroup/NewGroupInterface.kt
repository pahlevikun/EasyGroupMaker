package id.pahlevikun.easygroupmaker.presenter.interfaces.newgroup

import android.content.Context
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable
import id.pahlevikun.easygroupmaker.presenter.interfaces.callback.ItemTouchCallback
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAdapter

interface NewGroupInterface {

    fun isFieldEmpty(v1: String, v2: String): Boolean

    fun gettingData(context: Context): List<UserGroupTable>

    fun parsingBackToArray(value: String): Array<String>

    fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: List<UserGroupTable>, callback: ItemTouchCallback): NewGroupAdapter
}