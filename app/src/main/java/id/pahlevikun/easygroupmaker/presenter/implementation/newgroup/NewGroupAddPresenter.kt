package id.pahlevikun.easygroupmaker.presenter.implementation.newgroup

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import id.pahlevikun.easygroupmaker.model.database.RoomInitializer
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable
import id.pahlevikun.easygroupmaker.presenter.interfaces.newgroup.NewGroupAddInterface
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAddAdapter
import java.text.SimpleDateFormat
import java.util.*

class NewGroupAddPresenter : NewGroupAddInterface {

    override fun isFieldEmpty(v1: String, v2: String): Boolean {
        return v1.isEmpty() || v2.isEmpty()
    }

    @SuppressLint("SimpleDateFormat")
    override fun saveGroupList(context: Context, name: String, desc: String, array: ArrayList<String>) {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val date = Date()
        val createdAt = formatter.format(date)

        RoomInitializer
                .initDatabase(context)
                .userGroupDaoAccess()
                .insertSingleUserGroup(UserGroupTable(
                        name,
                        desc,
                        Arrays.deepToString(array.toArray()),
                        createdAt, createdAt))
        RoomInitializer.destroyGroupList()
    }

    override fun parsingToArray(arrayList: ArrayList<String>): Array<String> {
        val stockArr = arrayOfNulls<String>(arrayList.size)
        return arrayList.toArray(stockArr)
    }

    override fun isQuickFieldEmpty(sumOfGroup: String, sumOfPerson: String): Boolean {
        return !sumOfGroup.isEmpty() || !sumOfPerson.isEmpty()
    }

    override fun addItemAdapter(name: String, adapter: NewGroupAddAdapter, arrayList: ArrayList<String>): ArrayList<String> {
        arrayList.add(name)
        adapter.notifyDataSetChanged()
        return arrayList
    }

    override fun addItemToArrayList(arrayList: ArrayList<String>, name: String): ArrayList<String> {
        arrayList.add(name)
        return arrayList
    }

    override fun setupAdapter(context: Context, recyclerView: RecyclerView, arrayList: ArrayList<String>): NewGroupAddAdapter {
        val adapter = NewGroupAddAdapter(arrayList)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        return adapter
    }
}