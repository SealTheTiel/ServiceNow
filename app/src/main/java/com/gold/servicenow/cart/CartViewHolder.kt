package com.gold.servicenow.cart

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import java.util.concurrent.Executors

class CartViewHolder(itemView: View, adapter: CartAdapter): RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.itemImage)
    private val name: TextView = itemView.findViewById(R.id.itemName)
    private val description: TextView = itemView.findViewById(R.id.itemDescription)
    private val price: TextView = itemView.findViewById(R.id.itemPrice)
    private val quantity: EditText = itemView.findViewById(R.id.itemQuantity)
    private val decrement: ImageButton = itemView.findViewById(R.id.itemDecrement)
    private val increment: ImageButton = itemView.findViewById(R.id.itemIncrement)
    private val delete: ImageButton = itemView.findViewById(R.id.itemDelete)
    private lateinit var cartEntry: CartEntry

    fun bindData(cartEntry: CartEntry) {
        this.cartEntry = cartEntry
        name.text = cartEntry.name
        quantity.setText(String.format("%d", cartEntry.quantity))
        price.text = "PHP " + String.format("%.2f", cartEntry.price * cartEntry.quantity)

        val imageExecutor = Executors.newSingleThreadExecutor()
        val imageHandler = Handler(Looper.getMainLooper())
        var imageBitmap: Bitmap? = null

        imageExecutor.execute {
            try {
                val `in` = java.net.URL(cartEntry.imageUrl).openStream()
                imageBitmap = android.graphics.BitmapFactory.decodeStream(`in`)
                imageHandler.post {
                    image.setImageBitmap(imageBitmap)
                }
            } catch (e: Exception) {
                error("Error: ${e.message}")
                e.printStackTrace()
            }
        }

        description.visibility = View.GONE
    }

    init {
        delete.setOnClickListener {
            adapter.removeItem(adapterPosition)
            CartList.removeCartEntry(cartEntry)
        }

        increment.setOnClickListener {
            increment.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.button_click_scale))
            val newEntry = CartEntry(cartEntry.name, cartEntry.price, 1, cartEntry.imageUrl)
            CartList.addCartEntry(newEntry)
            val amount = CartList.getCartEntry(cartEntry.name)?.quantity ?: return@setOnClickListener
            quantity.setText(String.format("%d", amount))
        }
        decrement.setOnClickListener {
            decrement.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.button_click_scale))
            if (cartEntry.quantity == 1) {
                adapter.removeItem(adapterPosition)
                CartList.removeCartEntry(cartEntry)
                return@setOnClickListener
            }
            val newEntry = CartEntry(cartEntry.name, cartEntry.price, -1, cartEntry.imageUrl)
            CartList.addCartEntry(newEntry)
            val amount = CartList.getCartEntry(cartEntry.name)?.quantity ?: return@setOnClickListener
            quantity.setText(String.format("%d", amount))
        }
    }
}