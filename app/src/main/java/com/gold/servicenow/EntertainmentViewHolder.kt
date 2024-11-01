package com.gold.servicenow

import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class EntertainmentViewHolder(itemView: View): ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.leisureImage)
    private val name: TextView = itemView.findViewById(R.id.leisureName)
    private val purpose: TextView = itemView.findViewById(R.id.leisurePurpose)
    private val price: TextView = itemView.findViewById(R.id.leisurePrice)
    private val button: CardView = itemView.findViewById(R.id.leisureButton)
    private lateinit var leisure: Leisure

    fun bindData(leisure: Leisure) {
        this.leisure = leisure
        image.setImageResource(leisure.imageId)
        name.text = leisure.name
        purpose.text = leisure.description
        price.text = "PHP " + leisure.price.toString()
    }

    init {
        itemView.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(itemView.context)
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.activity_view_details)
        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(android.view.Gravity.BOTTOM)

        val dialogName: TextView = dialog.findViewById(R.id.addToCartName)
        val dialogDetail1: TextView = dialog.findViewById(R.id.addToCartDetail1)
        val dialogDetail2: TextView = dialog.findViewById(R.id.addToCartDetail2)
        val dialogDescription1: TextView = dialog.findViewById(R.id.addToCartDescription1)
        val dialogDescription2: TextView = dialog.findViewById(R.id.addToCartDescription2)
        val dialogIncrement: Button = dialog.findViewById(R.id.addToCartIncrement)
        val dialogDecrement: Button = dialog.findViewById(R.id.addToCartDecrement)
        val dialogQuantity: TextView = dialog.findViewById(R.id.addToCartQuantity)
        val dialogButton: Button = dialog.findViewById(R.id.addToCartButton)


        dialogName.text = leisure.name
        dialogDetail1.text = leisure.detail1
        dialogDetail2.text = leisure.detail2
        dialogDescription1.text = leisure.location
        dialogDescription2.text = leisure.contact
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
            val cartEntry = CartEntry(leisure.name, leisure.price, dialogQuantity.text.toString().toInt(), leisure.imageId)
            CartList.addCartEntry(cartEntry)
            dialog.dismiss()
        }
    }
}