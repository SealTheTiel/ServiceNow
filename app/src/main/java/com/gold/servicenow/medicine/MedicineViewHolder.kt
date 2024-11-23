package com.gold.servicenow.medicine

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
import com.gold.servicenow.databinding.ItemLayoutBinding
import java.util.concurrent.Executors

class MedicineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private lateinit var itemBinding: ItemLayoutBinding
    private lateinit var medicine: Medicine

    fun bindData(medicine: Medicine) {
        itemBinding = ItemLayoutBinding.bind(itemView)
        this.medicine = medicine
        itemBinding.itemName.text = medicine.name
        itemBinding.itemDescription.text = medicine.description
        itemBinding.itemPrice.text = "PHP " + medicine.price.toString()

        val imageExecutor = Executors.newSingleThreadExecutor()
        val imageHandler = Handler(Looper.getMainLooper())
        var imageBitmap: Bitmap? = null

        imageExecutor.execute {
            try {
                val `in` = java.net.URL(medicine.imageUrl).openStream()
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

        itemBinding.itemQuantityValue.visibility = View.GONE
        itemBinding.itemQuantity.visibility = View.GONE
        itemBinding.itemDecrement.visibility = View.GONE
        itemBinding.itemIncrement.visibility = View.GONE
        itemBinding.itemDelete.visibility = View.GONE
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


        dialogName.text = medicine.name
        dialogDetail1.text = medicine.detail1
        dialogDetail2.text = medicine.detail2
        dialogDescription1.text = medicine.dosage
        dialogDescription2.text = medicine.sideEffects
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
                medicine.name,
                medicine.price,
                dialogQuantity.text.toString().toInt(),
                medicine.imageUrl
            )
            CartList.addCartEntry(cartEntry)
            dialog.dismiss()
        }
    }
}