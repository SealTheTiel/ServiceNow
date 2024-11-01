package com.gold.servicenow

import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class FoodViewHolder(itemView: View): ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.foodImage)
    private val name: TextView = itemView.findViewById(R.id.foodName)
    private val price: TextView = itemView.findViewById(R.id.foodPrice)
    private val button: CardView = itemView.findViewById(R.id.foodButton)


    fun bindData(food: Food) {
        image.setImageResource(food.imageId)
        name.text = food.name
        price.text = "PHP " + food.price.toString()
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
    }
}