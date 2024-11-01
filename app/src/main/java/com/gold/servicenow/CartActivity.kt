package com.gold.servicenow

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : ComponentActivity(), CartChangeListener {
    private val cartList: ArrayList<CartEntry> = CartList.cartList
    private lateinit var total: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        enableEdgeToEdge()

        this.recyclerView = findViewById(R.id.cartRecycle)
        this.total = findViewById(R.id.cartTotal)

        this.total.text = "Total: PHP 0.00"
        CartList.addListener(this)
        val adapter = CartAdapter(this.cartList)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCartUpdated(totalPrice: Float) {
        total.text = "Total: PHP " + String.format("%.2f", CartList.getCartTotal())
    }

}