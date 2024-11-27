package com.gold.servicenow.cart

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import com.gold.servicenow.databinding.ItemLayoutBinding
import java.util.concurrent.Executors

class CartViewHolder(itemView: View, adapter: CartAdapter): RecyclerView.ViewHolder(itemView) {
    private lateinit var itemBinding: ItemLayoutBinding

    private lateinit var cartEntry: CartEntry

    fun bindData(cartEntry: CartEntry) {
        this.cartEntry = cartEntry
        itemBinding.itemName.text = cartEntry.name
        itemBinding.itemQuantityValue.setText(String.format("%d", cartEntry.quantity))
        itemBinding.itemPrice.text = "PHP " + String.format("%.2f", cartEntry.price * cartEntry.quantity)

        val imageExecutor = Executors.newSingleThreadExecutor()
        val imageHandler = Handler(Looper.getMainLooper())
        var imageBitmap: Bitmap? = null

        imageExecutor.execute {
            try {
                val `in` = java.net.URL(cartEntry.imageUrl).openStream()
                imageBitmap = android.graphics.BitmapFactory.decodeStream(`in`)
                imageHandler.post {
                    itemBinding.itemImage.setImageBitmap(imageBitmap)
                    itemBinding.itemLoading.visibility = View.GONE
                }
            } catch (e: Exception) {
                error("Error: ${e.message}")
                e.printStackTrace()
            }
        }

        itemBinding.itemDescription.visibility = View.GONE
        itemBinding.itemQuantity.visibility = View.GONE
    }

    init {
        itemBinding = ItemLayoutBinding.bind(itemView)
        itemBinding.itemDelete.setOnClickListener {
            adapter.removeItem(adapterPosition)
            CartList.removeCartEntry(cartEntry)
        }

        itemBinding.itemIncrement.setOnClickListener {
            itemBinding.itemIncrement.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.button_click_scale))
            val newEntry = CartEntry(cartEntry.name, cartEntry.price, 1, cartEntry.imageUrl)
            CartList.addCartEntry(newEntry)
            val amount = CartList.getCartEntry(cartEntry.name)?.quantity ?: return@setOnClickListener
            itemBinding.itemQuantityValue.setText(String.format("%d", amount))
            itemBinding.itemPrice.text = "PHP " + String.format("%.2f", cartEntry.price * amount)
        }
        itemBinding.itemDecrement.setOnClickListener {
            itemBinding.itemDecrement.startAnimation(AnimationUtils.loadAnimation(itemView.context, R.anim.button_click_scale))
            if (cartEntry.quantity == 1) {
                adapter.removeItem(adapterPosition)
                CartList.removeCartEntry(cartEntry)
                return@setOnClickListener
            }
            val newEntry = CartEntry(cartEntry.name, cartEntry.price, -1, cartEntry.imageUrl)
            CartList.addCartEntry(newEntry)
            val amount = CartList.getCartEntry(cartEntry.name)?.quantity ?: return@setOnClickListener
            itemBinding.itemQuantityValue.setText(String.format("%d", amount))
            itemBinding.itemPrice.text = "PHP " + String.format("%.2f", cartEntry.price * amount)
        }
    }
}