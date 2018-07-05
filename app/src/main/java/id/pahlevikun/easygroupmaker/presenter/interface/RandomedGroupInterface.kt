package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.content.Context
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAdapter

interface RandomedGroupInterface {

    fun gettingData(context: Context): List<UserGroupTable>

    fun parsingBackToArray(value: String): Array<String>

    fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: List<UserGroupTable>, callback: ItemTouchCallback): NewGroupAdapter
}