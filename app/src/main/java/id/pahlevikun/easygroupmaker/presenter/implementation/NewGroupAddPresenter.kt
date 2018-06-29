package id.pahlevikun.easygroupmaker.presenter.implementation

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.model.pojo.NewGroup
import id.pahlevikun.easygroupmaker.presenter.`interface`.NewGroupAddInterface
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAddlAdapter

class NewGroupAddPresenter : NewGroupAddInterface {

    override fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean {
        return !sumOfGroup.isEmpty() || !sumOfPerson.isEmpty()
    }

    override fun addItemAdapter(name: String, adapter: NewGroupAddlAdapter, arrayList: ArrayList<NewGroup>): ArrayList<NewGroup> {
        arrayList.add(NewGroup(name))
        adapter.notifyDataSetChanged()
        return arrayList
    }

    override fun addItemToArrayList(arrayList: ArrayList<NewGroup>, name: String): ArrayList<NewGroup> {
        arrayList.add(NewGroup(name))
        return arrayList
    }

    override fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: ArrayList<NewGroup>): NewGroupAddlAdapter {
        val adapter = NewGroupAddlAdapter(arrayList)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return adapter
    }
}