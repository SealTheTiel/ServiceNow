package com.gold.servicenow.carthistory

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import com.gold.servicenow.SuccessActivity
import com.gold.servicenow.cart.CartEntry
import com.gold.servicenow.cart.CartList

class CartHistoryActivity :ComponentActivity() {
    private val cartList: ArrayList<CartEntry> = CartList.cartList
    private lateinit var cartAdapter: CartHistoryAdapter
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var sp: SharedPreferences
    private lateinit var total: TextView
    private lateinit var confirmButton: Button
    private lateinit var location: TextView
    private lateinit var name: TextView
    private lateinit var sp1: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart_history)
        this.cartRecyclerView = findViewById(R.id.checkoutRecycle)
        this.cartAdapter = CartHistoryAdapter(this.cartList)

        sp = getSharedPreferences("cart", MODE_PRIVATE)
        loadCartFromPreferences()

        this.cartRecyclerView.adapter = cartAdapter
        this.cartRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        this.total = findViewById(R.id.total_price)
        this.confirmButton = findViewById(R.id.confirmOrderButton)
        this.location = findViewById(R.id.location_label)
        this.name = findViewById(R.id.user_label)

        this.total.text = "Total: PHP ${CartList.getCartTotal()}"

        // get name from sp1
        sp1 = getSharedPreferences("ServiceNowPrefs", MODE_PRIVATE)
        val username = sp1.getString("name", "User")
        name.text = username
        location.setText( intent.getStringExtra("selectedLocation") ?: "Location not set")
        confirmButton.setOnClickListener {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)

        }


    }

    private fun loadCartFromPreferences() {
        val cartSize = sp.getInt("cartSize", 0)

        // Ensure we don't add duplicate items
        for (i in 0 until cartSize) {
            val name = sp.getString("cartItem$i", "") ?: ""
            val quantity = sp.getInt("cartQuantity$i", 0)
            val price = sp.getFloat("cartPrice$i", 0.0f)
            val imageUrl = sp.getString("cartImage$i", "") ?: ""

            if (name.isNotEmpty() && imageUrl.isNotEmpty()) {
                val existingEntry = CartList.cartList.find { it.name == name }

                if (existingEntry != null) {
                    // Update the quantity of the existing item
                    existingEntry.quantity = quantity
                } else {
                    // Add a new item if it doesn't exist
                    CartList.addCartEntry(CartEntry(name, price, quantity, imageUrl))
                }
            }
        }

        // Notify the cart list that data has been loaded
        CartList.notifyListener()
    }
}