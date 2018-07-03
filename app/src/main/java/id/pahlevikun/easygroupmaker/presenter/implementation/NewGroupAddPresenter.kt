package id.pahlevikun.easygroupmaker.presenter.implementation

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.presenter.`interface`.NewGroupAddInterface
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAddlAdapter

class NewGroupAddPresenter : NewGroupAddInterface {

    override fun parsingToArray(arrayList: ArrayList<String>): Array<String> {
        val stockArr = arrayOfNulls<String>(arrayList.size)
        return arrayList.toArray(stockArr)
    }

    override fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean {
        return !sumOfGroup.isEmpty() || !sumOfPerson.isEmpty()
    }

    override fun addItemAdapter(name: String, adapter: NewGroupAddlAdapter, arrayList: ArrayList<String>): ArrayList<String> {
        arrayList.add(name)
        adapter.notifyDataSetChanged()
        return arrayList
    }

    override fun addItemToArrayList(arrayList: ArrayList<String>, name: String): ArrayList<String> {
        arrayList.add(name)
        return arrayList
    }

    override fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: ArrayList<String>): NewGroupAddlAdapter {
        val adapter = NewGroupAddlAdapter(arrayList)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return adapter
    }
}