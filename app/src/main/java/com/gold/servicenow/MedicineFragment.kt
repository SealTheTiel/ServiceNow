package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedicineFragment : Fragment() {
    private lateinit var cart: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and get the view object
        val view = inflater.inflate(R.layout.fragment_medicine, container, false)

        // Initialize cart button
        cart = view.findViewById(R.id.cartMedicine)
        cart.setOnClickListener {
            // Navigate to AddtoCart activity
            val intent = Intent(context, AddtoCart::class.java)
            startActivity(intent)
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val medicineList: ArrayList<Medicine> = DataGenerator.getMedicine()
        val recyclerView: RecyclerView = view.findViewById(R.id.medicineRecycle)
        val adapter = MedicineAdapter(medicineList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        cart = view.findViewById(R.id.cartMedicine)
        cart.setOnClickListener {
            val intent = Intent(context, CartActivity::class.java)
            startActivity(intent)
        }
    }
}