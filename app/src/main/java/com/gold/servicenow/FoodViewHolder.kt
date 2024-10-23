package com.gold.servicenow

import android.view.View
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

}