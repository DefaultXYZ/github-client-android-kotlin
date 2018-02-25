package com.defaultxyz.githubclient.ui.main.utils

import android.content.Context
import android.content.Intent
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.model.DataItem
import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User
import com.defaultxyz.githubclient.ui.details.DetailsActivity

class ResultAdapter(private val context: Context): RecyclerView.Adapter<ResultAdapter.DataItemHolder>() {
    private var data: List<DataItem> = emptyList()

    fun setData(data: List<DataItem>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataItemHolder {
        val inflater = LayoutInflater.from(context)
        val type = ItemType.fromCode(viewType)
        val view = inflater.inflate(type.layout!!, parent, false)
        return DataItemHolder(view)
    }

    override fun onBindViewHolder(holder: DataItemHolder?, position: Int) {
        val item = data[position]
        if (holder == null) return
        holder.title.text = item.title
        if (item is User) {
            holder.container.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return ItemType.fromObject(data[position]).code
    }

    inner class DataItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: ConstraintLayout = view as ConstraintLayout
        val title: TextView = view.findViewById(R.id.title)
    }

    enum class ItemType(val code: Int,
                        @LayoutRes val layout: Int?) {
        USER(0, R.layout.item_user),
        REPOSITORY(1, R.layout.item_repository),
        UNKNOWN(-1, null);

        companion object {
            fun fromObject(obj: Any): ItemType {
                return when (obj) {
                    is User -> USER
                    is Repository -> REPOSITORY
                    else -> UNKNOWN
                }
            }

            fun fromCode(code: Int): ItemType {
                return ItemType.values().find { it.code == code } ?: UNKNOWN
            }
        }
    }
}