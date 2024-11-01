package com.gold.servicenow

import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CartViewHolder(itemView: View, adapter: CartAdapter): ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.cartName)
    private val image: ImageView = itemView.findViewById(R.id.cartImage)
    private val quantity: TextView = itemView.findViewById(R.id.cartQuantity)
    private val price: TextView = itemView.findViewById(R.id.cartPrice)
    private val delete: ImageButton = itemView.findViewById(R.id.cartDelete)
    private val increment: ImageButton = itemView.findViewById(R.id.cartIncrement)
    private val decrement: ImageButton = itemView.findViewById(R.id.cartDecrement)
    private lateinit var cartEntry: CartEntry

    fun bindData(cartEntry: CartEntry) {
        this.cartEntry = cartEntry
        name.text = cartEntry.name
        image.setImageResource(cartEntry.imageId)
        quantity.text = cartEntry.quantity.toString()
        price.text = "PHP " + String.format("%.2f", cartEntry.price * cartEntry.quantity)
    }

    init {
        delete.setOnClickListener {
            adapter.removeItem(adapterPosition)
            CartList.removeCartEntry(cartEntry)
        }

        increment.setOnClickListener {
            val newEntry = CartEntry(cartEntry.name, cartEntry.price, 1, cartEntry.imageId)
            CartList.addCartEntry(newEntry)
            quantity.text = CartList.getCartEntry(cartEntry.name)?.quantity.toString()
        }
        decrement.setOnClickListener {
            if (cartEntry.quantity == 1) {
                adapter.removeItem(adapterPosition)
                CartList.removeCartEntry(cartEntry)
                return@setOnClickListener
            }
            val newEntry = CartEntry(cartEntry.name, cartEntry.price, -1, cartEntry.imageId)
            CartList.addCartEntry(newEntry)
            quantity.text = CartList.getCartEntry(cartEntry.name)?.quantity.toString()
        }
    }
}