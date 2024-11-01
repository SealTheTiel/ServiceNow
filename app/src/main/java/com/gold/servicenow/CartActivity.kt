package com.gold.servicenow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : ComponentActivity() {
    private val cartList: ArrayList<CartEntry> = CartList.cartList
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        enableEdgeToEdge()

        this.recyclerView = findViewById(R.id.cartRecycle)

        this.recyclerView.adapter = CartAdapter(this.cartList)

        this.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}