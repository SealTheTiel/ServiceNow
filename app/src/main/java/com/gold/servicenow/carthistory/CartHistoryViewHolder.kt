package com.gold.servicenow.carthistory

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
import com.gold.servicenow.cart.CartAdapter
import com.gold.servicenow.cart.CartEntry
import com.gold.servicenow.cart.CartList
import java.util.concurrent.Executors

class CartHistoryViewHolder(itemView: View, adapter: CartHistoryAdapter): RecyclerView.ViewHolder(itemView)  {
        private val image: ImageView = itemView.findViewById(R.id.itemImage)
        private val name: TextView = itemView.findViewById(R.id.itemName)
        private val description: TextView = itemView.findViewById(R.id.itemDescription)
        private val price: TextView = itemView.findViewById(R.id.itemPrice)
        private val quantity: EditText = itemView.findViewById(R.id.itemQuantity)
        private val decrement: ImageButton = itemView.findViewById(R.id.itemDecrement)
        private val increment: ImageButton = itemView.findViewById(R.id.itemIncrement)
        private val delete: ImageButton = itemView.findViewById(R.id.itemDelete)
        private val loading: ProgressBar = itemView.findViewById(R.id.itemLoading)

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
                        loading.visibility = View.GONE
                    }
                } catch (e: Exception) {
                    error("Error: ${e.message}")
                    e.printStackTrace()
                }
            }

            delete.visibility = View.GONE
            increment.visibility = View.GONE
            decrement.visibility = View.GONE
            description.visibility = View.GONE

        }


    }