package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class SuccessActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_success)

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
}
