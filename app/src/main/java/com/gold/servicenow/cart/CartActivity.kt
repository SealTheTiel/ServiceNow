package com.gold.servicenow.cart

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gold.servicenow.R
import com.gold.servicenow.SetLocationActivity

class CartActivity : ComponentActivity(), CartChangeListener {
    private val cartList: ArrayList<CartEntry> = CartList.cartList
    private lateinit var total: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var checkout: Button
    private lateinit var sp: SharedPreferences
    private lateinit var emptyText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.updatePadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

//        private val cartUpdateReceiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                if (intent?.action == "cartUpdated") {
//                    clearCart()
//                    Log.d("CartActivity", "Cart cleared and updated!")
//                }
//            }
//        }

        this.recyclerView = findViewById(R.id.cartRecycle)
        this.total = findViewById(R.id.cartTotal)
        this.emptyText = findViewById(R.id.cartEmptyText)
        this.total.text = "Total: PHP 0.00"
        this.checkout = findViewById(R.id.cartCheckoutButton)


        val adapter = CartAdapter(this.cartList)
        this.recyclerView.adapter = adapter

        // Load cart data and update cart items
        sp = getSharedPreferences("cart", MODE_PRIVATE)
        loadCartFromPreferences()

        // Set total to show initial amount (or 0.00 if cart is empty)

        this.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        CartList.addListener(this)
        checkout.setOnClickListener {
            val intent = Intent(this, SetLocationActivity::class.java)
            startActivity(intent)
        }
    }

    // Load cart from SharedPreferences and update existing items if found
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

    override fun onCartUpdated(totalPrice: Float) {
        if (cartList.size == 0) {
            emptyText.visibility = View.VISIBLE
            total.visibility = View.GONE
            checkout.visibility = View.GONE
        }
        else {
            emptyText.visibility = View.GONE
            total.visibility = View.VISIBLE
            checkout.visibility = View.VISIBLE
        }
        saveCartToPreferences()
        total.text = "Total: PHP ${String.format("%.2f", totalPrice)}"
    }

    // Save cart to SharedPreferences (ensuring updated quantities are stored)
    private fun saveCartToPreferences() {
        val editor = sp.edit()
        val cartList = CartList.cartList
        editor.clear() // Clear previous data

        // Save cart size
        editor.putInt("cartSize", cartList.size)

        // Save each cart item
        for (i in 0 until cartList.size) {
            editor.putString("cartItem$i", cartList[i].name)
            editor.putInt("cartQuantity$i", cartList[i].quantity)
            editor.putFloat("cartPrice$i", cartList[i].price)
            editor.putString("cartImage$i", cartList[i].imageUrl)
        }

        // Save the total price
        editor.putFloat("total", CartList.getCartTotal())

        editor.apply() // Save changes
        Log.d("CartActivity", "Cart saved to SharedPreferences: size = ${cartList.size}")
    }

//    private fun clearCart() {
//        // Clear the cart data in memory
//        cartList.clear()
//        CartList.cartList.clear()
//        CartList.notifyListener()
//
//        // Clear SharedPreferences
//        sp.edit().clear().apply()
//
//
//    }

}
