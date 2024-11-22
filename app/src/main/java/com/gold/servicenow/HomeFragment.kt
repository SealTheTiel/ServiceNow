package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.gold.servicenow.entertainment.EntertainmentFragment
import com.gold.servicenow.food.FoodFragment
import com.gold.servicenow.medicine.MedicineFragment
import com.gold.servicenow.profile.CurrentProfile
import com.gold.servicenow.profile.Profile
import com.gold.servicenow.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var medicineButton: CardView
    private lateinit var foodButton: CardView
    private lateinit var entertainmentButton: CardView
    private lateinit var profileButton: CardView
    private lateinit var username: TextView
    private lateinit var profileImage: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize buttons
        medicineButton = view.findViewById(R.id.medicine_button)
        foodButton = view.findViewById(R.id.food_button)
        entertainmentButton = view.findViewById(R.id.entertainment_button)
        profileButton = view.findViewById(R.id.profile_button)
        username = view.findViewById(R.id.profile_label)
        profileImage = view.findViewById(R.id.profileImageViewHome)
        println(CurrentProfile.profile?.name)
        println(CurrentProfile.profile?.email)
        println(CurrentProfile.profile?.contact)
        println(CurrentProfile.profile?.password)

        if (CurrentProfile.profile?.image != "") {
            profileImage.setImageBitmap(CurrentProfile.convertBase64ToBitmap(CurrentProfile.profile?.image!!))
        }

        username.text = CurrentProfile.profile?.name
        // Set click listeners for each button
        medicineButton.setOnClickListener {
            replaceFragment(MedicineFragment(), R.id.medicine)
        }

        foodButton.setOnClickListener {
            replaceFragment(FoodFragment(), R.id.food)
        }

        entertainmentButton.setOnClickListener {
            replaceFragment(EntertainmentFragment(), R.id.entertainment)
        }

        profileButton.setOnClickListener {
            val intent = Intent(activity, ProfileActivity   ::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        if (CurrentProfile.profile?.image != "") {
            profileImage.setImageBitmap(CurrentProfile.convertBase64ToBitmap(CurrentProfile.profile?.image!!))
        }
    }
    override fun onStart() {
        super.onStart()
        if (CurrentProfile.profile?.image != "") {
            profileImage.setImageBitmap(CurrentProfile.convertBase64ToBitmap(CurrentProfile.profile?.image!!))
        }
    }
    private fun replaceFragment(fragment: Fragment, selected: Int) {
        var fragmentTransition = parentFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame, fragment)
        //fragmentTransition.addToBackStack(null)
        fragmentTransition.commit()

        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.navbar)?.selectedItemId = selected

    }

}
