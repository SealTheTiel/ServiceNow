package com.gold.servicenow

import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MedicineViewHolder(itemView: View): ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.medicineImage)
    private val name: TextView = itemView.findViewById(R.id.medicineName)
    private val purpose: TextView = itemView.findViewById(R.id.medicinePurpose)
    private val price: TextView = itemView.findViewById(R.id.medicinePrice)
    private val button: CardView = itemView.findViewById(R.id.medicineButton)
    private lateinit var medicine: Medicine

    fun bindData(medicine: Medicine) {
        this.medicine = medicine
        image.setImageResource(medicine.imageId)
        name.text = medicine.name
        purpose.text = medicine.description
        price.text = "PHP " + medicine.price.toString()
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
            val cartEntry = CartEntry(medicine.name, medicine.price, dialogQuantity.text.toString().toInt(), medicine.imageId)
            CartList.addCartEntry(cartEntry)
            dialog.dismiss()
        }
    }
}