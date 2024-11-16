package com.gold.servicenow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.recyclerview.widget.RecyclerView.Adapter

class EntertainmentAdapter(private val data: ArrayList<Leisure>): Adapter<EntertainmentViewHolder>() {
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