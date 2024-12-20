package com.gold.servicenow.medicine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R

class MedicineAdapter(private val data: ArrayList<Medicine>): RecyclerView.Adapter<MedicineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout, parent, false)
        return MedicineViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bindData(this.data[position])
    }

    override fun getItemCount(): Int {
        return this.data.size
    }
}