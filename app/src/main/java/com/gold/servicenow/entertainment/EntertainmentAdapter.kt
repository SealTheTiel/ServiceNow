package com.gold.servicenow.entertainment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R

class EntertainmentAdapter(private val data: ArrayList<Entertainment>): RecyclerView.Adapter<EntertainmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntertainmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout, parent, false)
        return EntertainmentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntertainmentViewHolder, position: Int) {
        holder.bindData(this.data[position])
    }

    override fun getItemCount(): Int {
        return this.data.size
    }
}