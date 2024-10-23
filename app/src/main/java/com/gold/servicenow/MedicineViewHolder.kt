package com.gold.servicenow

import android.view.View
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

    fun bindData(medicine: Medicine) {
        image.setImageResource(medicine.imageId)
        name.text = medicine.name
        purpose.text = medicine.description
        price.text = "PHP " + medicine.price.toString()
    }

}