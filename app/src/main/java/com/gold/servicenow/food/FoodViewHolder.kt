package com.gold.servicenow.food

import android.app.Dialog
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import com.gold.servicenow.cart.CartEntry
import com.gold.servicenow.cart.CartList
import java.util.concurrent.Executors

class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val image: ImageView = itemView.findViewById(R.id.itemImage)
    private val name: TextView = itemView.findViewById(R.id.itemName)
    private val description: TextView = itemView.findViewById(R.id.itemDescription)
    private val price: TextView = itemView.findViewById(R.id.itemPrice)
    private val quantity: EditText = itemView.findViewById(R.id.itemQuantity)
    private val decrement: ImageButton = itemView.findViewById(R.id.itemDecrement)
    private val increment: ImageButton = itemView.findViewById(R.id.itemIncrement)
    private val delete: ImageButton = itemView.findViewById(R.id.itemDelete)
    private val loading: ProgressBar = itemView.findViewById(R.id.itemLoading)

    private lateinit var food: Food


    fun bindData(food: Food) {
        this.food = food
        name.text = food.name
        price.text = "PHP " + food.price.toString()
        val imageExecutor = Executors.newSingleThreadExecutor()
        val imageHandler = Handler(Looper.getMainLooper())
        var imageBitmap: Bitmap? = null

        imageExecutor.execute {
            try {
                val `in` = java.net.URL(food.imageUrl).openStream()
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

        description.visibility = View.GONE
        quantity.visibility = View.GONE
        decrement.visibility = View.GONE
        increment.visibility = View.GONE
        delete.visibility = View.GONE
    }

    init {
        itemView.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(itemView.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.activity_view_details)
        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)

        val dialogName: TextView = dialog.findViewById(R.id.addToCartName)
        val dialogDetail1: TextView = dialog.findViewById(R.id.addToCartDetail1)
        val dialogDetail2: TextView = dialog.findViewById(R.id.addToCartDetail2)
        val dialogDescription1: TextView = dialog.findViewById(R.id.addToCartDescription1)
        val dialogDescription2: TextView = dialog.findViewById(R.id.addToCartDescription2)
        val dialogIncrement: ImageButton = dialog.findViewById(R.id.addToCartIncrement)
        val dialogDecrement: ImageButton = dialog.findViewById(R.id.addToCartDecrement)
        val dialogQuantity: TextView = dialog.findViewById(R.id.addToCartQuantity)
        val dialogButton: Button = dialog.findViewById(R.id.addToCartButton)


        dialogName.text = food.name
        dialogDetail1.text = food.detail1
        dialogDetail2.text = food.detail2
        dialogDescription1.text = food.restaurant
        dialogDescription2.text = food.description
        dialogIncrement.setOnClickListener{
            var quantity = dialogQuantity.text.toString().toInt()
            quantity++
            dialogQuantity.text = quantity.toString()
        }
        dialogDecrement.setOnClickListener{
            var quantity = dialogQuantity.text.toString().toInt()
            if(quantity > 1) {
                quantity--
                dialogQuantity.text = quantity.toString()
            }
        }
        dialogButton.setOnClickListener {
            val cartEntry = CartEntry(
                food.name,
                food.price,
                dialogQuantity.text.toString().toInt(),
                food.imageUrl
            )
            CartList.addCartEntry(cartEntry)
            dialog.dismiss()
        }
    }
}