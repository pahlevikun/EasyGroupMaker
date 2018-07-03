package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.content.Context
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAddAdapter

interface NewGroupAddInterface {

    fun saveGroupList(context: Context, array: ArrayList<String>)

    fun parsingToArray(arrayList: ArrayList<String>): Array<String>

    fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean

    fun addItemAdapter(name: String, adapter: NewGroupAddAdapter, arrayList: ArrayList<String>): ArrayList<String>

    fun addItemToArrayList(arrayList: ArrayList<String>, name: String): ArrayList<String>

    fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: ArrayList<String>): NewGroupAddAdapter
}