@file:Suppress("DEPRECATION")

package id.pahlevikun.easygroupmaker.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.pahlevikun.easygroupmaker.R
import kotlinx.android.synthetic.main.adapter_new_group_add.view.*
import java.util.*


/**
 * Created by farhan on 6/30/17.
 */

class NewGroupAddlAdapter(private val nameData: ArrayList<String>) :
        RecyclerView.Adapter<NewGroupAddlAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_new_group_add, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemView.textViewName.text = nameData[i]
    }

    override fun getItemCount(): Int {
        return nameData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.textViewName
        }
    }

}

