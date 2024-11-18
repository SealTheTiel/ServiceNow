package com.gold.servicenow

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.fragment.app.Fragment
import com.gold.servicenow.databinding.ActivityMainBinding
import com.gold.servicenow.entertainment.EntertainmentFragment
import com.gold.servicenow.food.FoodFragment
import com.gold.servicenow.medicine.MedicineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gold.servicenow.database.DatabaseHandler
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private lateinit var navbar: BottomNavigationView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val databaseHandler = DatabaseHandler()

        // Insert dummy data -> Uncomment if there is new dummy data, simply change Data Generator
//        databaseHandler.insertFoodData()
//        databaseHandler.insertMedicineData()
//        databaseHandler.insertEntertainmentData()

        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.updatePadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        navbar = findViewById(R.id.navbar)
        navbar.itemIconTintList = null

        // Set up initial fragment
        if (intent.getBooleanExtra("openHomeFragment", false)) {
            replaceFragment(HomeFragment())
            navbar.selectedItemId = R.id.home
        } else {
            replaceFragment(HomeFragment())
        }

        binding.navbar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment(), addToBackStack = false)
                R.id.medicine -> replaceFragment(MedicineFragment(), addToBackStack = false)
                R.id.food -> replaceFragment(FoodFragment(), addToBackStack = false)
                R.id.entertainment -> replaceFragment(EntertainmentFragment(), addToBackStack = false)
                else -> {}
            }
            true
        }

        // Listen for changes in the back stack
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.frame)
            when (currentFragment) {
                is HomeFragment -> navbar.selectedItemId = R.id.home
                is MedicineFragment -> navbar.selectedItemId = R.id.medicine
                is FoodFragment -> navbar.selectedItemId = R.id.food
                is EntertainmentFragment -> navbar.selectedItemId = R.id.entertainment
            }
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()  // Go back in the stack if possible
        } else {
            // If on the home fragment or no more fragments, exit or minimize the app
            val currentFragment = supportFragmentManager.findFragmentById(R.id.frame)
            if (currentFragment is HomeFragment) {
                finish()
            } else {
                replaceFragment(HomeFragment(), addToBackStack = false)
                navbar.selectedItemId = R.id.home
            }
        }
    }


    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Check if the fragment is already displayed
        val currentFragment = fragmentManager.findFragmentById(R.id.frame)
        if (currentFragment != null && currentFragment::class.java == fragment::class.java) {
            return  // Avoid replacing the fragment if it's already displayed
        }

        // Clear back stack if not adding to back stack (for BottomNavigationView items)
        if (!addToBackStack) {
            fragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        // Replace fragment and conditionally add to back stack
        fragmentTransaction.replace(R.id.frame, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    fun updateNavbarSelection(selectedId: Int) {
        navbar.selectedItemId = selectedId
    }


}
