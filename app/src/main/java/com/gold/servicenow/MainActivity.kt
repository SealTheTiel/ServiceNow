package com.gold.servicenow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.gold.servicenow.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navbar: BottomNavigationView
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navbar = findViewById(R.id.navbar)
        navbar.itemIconTintList = null
        replaceFragment(HomeFragment())

        binding.navbar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.medicine -> replaceFragment(MedicineFragment())
                R.id.food -> replaceFragment(FoodFragment())
                R.id.entertainment -> replaceFragment(EntertainmentFragment())
                else -> {}
            }
            true
        }
    }

    // Function to replace fragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)  // Make sure 'frame' exists in your XML layout
            .addToBackStack(null)  // Optional, add to back stack if needed
            .commit()
    }
}
