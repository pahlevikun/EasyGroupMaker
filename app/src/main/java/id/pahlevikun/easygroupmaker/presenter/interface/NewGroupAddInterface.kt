package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.content.Context
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.model.pojo.NewGroup
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAddlAdapter

interface NewGroupAddInterface {

    fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean

    fun addItemAdapter(name: String, adapter: NewGroupAddlAdapter, arrayList: ArrayList<NewGroup>): ArrayList<NewGroup>

    fun addItemToArrayList(arrayList: ArrayList<NewGroup>, name: String): ArrayList<NewGroup>

    fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: ArrayList<NewGroup>): NewGroupAddlAdapter
}