package com.gold.servicenow.carthistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import com.gold.servicenow.cart.CartEntry
import com.gold.servicenow.carthistory.CartHistoryViewHolder

class CartHistoryAdapter (private val data: ArrayList<CartEntry>): RecyclerView.Adapter<CartHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHistoryViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout_checkout, parent, false)
        return CartHistoryViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: CartHistoryViewHolder, position: Int) {
        holder.bindData(this.data[position])
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun removeItem(position: Int) {
        this.data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, 1)
    }
}