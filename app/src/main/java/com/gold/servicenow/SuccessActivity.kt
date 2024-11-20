package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import com.gold.servicenow.cart.CartList

class SuccessActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_success)
        clearSharedPreferences()
        // NOTIFY CART FRAGMENT TO UPDATE THE CART
        CartList.clearCart()
        CartList.notifyListener()



        supportActionBar?.hide()
        backButton = findViewById(R.id.successBackButton)
        backButton.setOnClickListener {
            // Navigate back to MainActivity and open HomeFragment
            navigateToHomeFragment()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        // Navigate back to MainActivity and open HomeFragment
        navigateToHomeFragment()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Also handle the system back button
        navigateToHomeFragment()
    }

    private fun navigateToHomeFragment() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("openHomeFragment", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun clearSharedPreferences(){
        val sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
