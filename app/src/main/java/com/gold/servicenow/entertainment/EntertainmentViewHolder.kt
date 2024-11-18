package com.gold.servicenow.entertainment

import android.app.Dialog
import android.graphics.Bitmap
import android.os.Debug
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
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import com.gold.servicenow.cart.CartEntry
import com.gold.servicenow.cart.CartList
import java.util.concurrent.Executors

class EntertainmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.itemImage)
    private val name: TextView = itemView.findViewById(R.id.itemName)
    private val description: TextView = itemView.findViewById(R.id.itemDescription)
    private val price: TextView = itemView.findViewById(R.id.itemPrice)
    private val quantity: EditText = itemView.findViewById(R.id.itemQuantity)
    private val decrement: ImageButton = itemView.findViewById(R.id.itemDecrement)
    private val increment: ImageButton = itemView.findViewById(R.id.itemIncrement)
    private val delete: ImageButton = itemView.findViewById(R.id.itemDelete)
    private lateinit var entertainment: Entertainment

    fun bindData(entertainment: Entertainment) {
        println("Entertainmen ID: ${entertainment.id}\n" +
                "\tName: ${entertainment.name}\n" +
                "\tDescription: ${entertainment.description}\n" +
                "\tPrice: ${entertainment.price}\n" +
                "\tImage ID: ${entertainment.imageUrl}\n" +
                "\tDetail 1: ${entertainment.detail1}\n" +
                "\tDetail 2: ${entertainment.detail2}\n")

        this.entertainment = entertainment
        name.text = entertainment.name
        description.text = entertainment.description
        price.text = "PHP " + entertainment.price.toString()

        val imageExecutor = Executors.newSingleThreadExecutor()
        val imageHandler = Handler(Looper.getMainLooper())
        var imageBitmap: Bitmap? = null

        imageExecutor.execute {
            try {
                val `in` = java.net.URL(entertainment.imageUrl).openStream()
                imageBitmap = android.graphics.BitmapFactory.decodeStream(`in`)
                imageHandler.post {
                    image.setImageBitmap(imageBitmap)
                }
            } catch (e: Exception) {
                error("Error: ${e.message}")
                e.printStackTrace()
            }
        }

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


        dialogName.text = entertainment.name
        dialogDetail1.text = entertainment.detail1
        dialogDetail2.text = entertainment.detail2
        dialogDescription1.text = entertainment.location
        dialogDescription2.text = entertainment.contact
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
                entertainment.name,
                entertainment.price,
                dialogQuantity.text.toString().toInt(),
                entertainment.imageUrl
            )
            CartList.addCartEntry(cartEntry)
            dialog.dismiss()
        }
    }
}