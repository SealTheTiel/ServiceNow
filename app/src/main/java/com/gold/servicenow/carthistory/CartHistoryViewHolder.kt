package com.gold.servicenow.carthistory

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.View
    import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import com.gold.servicenow.cart.CartEntry
import com.gold.servicenow.databinding.ItemLayoutBinding
import java.util.concurrent.Executors

class CartHistoryViewHolder(itemView: View, adapter: CartHistoryAdapter): RecyclerView.ViewHolder(itemView)  {
        private lateinit var itemBinding: ItemLayoutBinding

        private lateinit var cartEntry: CartEntry

        fun bindData(cartEntry: CartEntry) {
            itemBinding = ItemLayoutBinding.bind(itemView)
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
            itemBinding.itemIncrement.visibility = View.GONE
            itemBinding.itemDecrement.visibility = View.GONE
            itemBinding.itemDelete.visibility = View.GONE

            val constraintSet = ConstraintSet()
            constraintSet.clone(itemBinding.itemDetails)
            constraintSet.connect(itemBinding.itemQuantityValue.id, ConstraintSet.TOP, ConstraintSet.GONE, ConstraintSet.TOP)
            constraintSet.connect(itemBinding.itemQuantityValue.id, ConstraintSet.START, itemBinding.itemQuantity.id, ConstraintSet.END)
            constraintSet.connect(itemBinding.itemQuantityValue.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            constraintSet.applyTo(itemBinding.itemDetails)
        }


    }