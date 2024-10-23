package com.gold.servicenow

import android.view.View
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

    fun bindData(leisure: Leisure) {
        image.setImageResource(leisure.imageId)
        name.text = leisure.name
        purpose.text = leisure.description
        price.text = "PHP " + leisure.price.toString()
    }

}