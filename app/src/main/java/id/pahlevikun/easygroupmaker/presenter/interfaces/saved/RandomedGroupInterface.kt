package id.pahlevikun.easygroupmaker.presenter.interfaces.saved

import android.content.Context
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.model.database.grouplist.RandomGroupListTable
import id.pahlevikun.easygroupmaker.presenter.interfaces.callback.ItemTouchCallback
import id.pahlevikun.easygroupmaker.view.adapter.SavedRandomAdapter

interface RandomedGroupInterface {

    fun gettingData(context: Context): List<RandomGroupListTable>

    fun parsingBackToArray(value: String): Array<String>

    fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayListRandom: List<RandomGroupListTable>, callback: ItemTouchCallback): SavedRandomAdapter
}