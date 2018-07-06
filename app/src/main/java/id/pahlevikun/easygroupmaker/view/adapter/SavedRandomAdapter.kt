@file:Suppress("DEPRECATION")

package id.pahlevikun.easygroupmaker.view.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.model.database.grouplist.RandomGroupListTable
import id.pahlevikun.easygroupmaker.presenter.interfaces.callback.ItemTouchCallback
import id.pahlevikun.easygroupmaker.presenter.implementation.newgroup.NewGroupPresenter
import kotlinx.android.synthetic.main.adapter_new_group.view.*


/**
 * Created by farhan on 6/30/17.
 */

class SavedRandomAdapter(private val nameData: List<RandomGroupListTable>,
                         private val onItemClick: ItemTouchCallback) :
        RecyclerView.Adapter<SavedRandomAdapter.ViewHolder>() {

    private val presenter = NewGroupPresenter()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_new_group, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemView.textViewName.text = nameData[i].name
        viewHolder.itemView.textViewSubName.text = "${presenter.parsingBackToArray(nameData[i].team).size} person"
        viewHolder.itemView.linearLayoutItem.setOnClickListener {
            onItemClick.onItemTouch(presenter.parsingBackToArray(nameData[i].team))
        }
    }

    override fun getItemCount(): Int {
        return nameData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.textViewName
            itemView.textViewSubName
            itemView.linearLayoutItem
        }
    }

}

