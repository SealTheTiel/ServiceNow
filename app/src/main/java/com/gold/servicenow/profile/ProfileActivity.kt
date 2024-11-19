package com.gold.servicenow.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.gold.servicenow.InputValidator
import com.gold.servicenow.R
import com.gold.servicenow.databinding.ActivityMainBinding
import com.gold.servicenow.entertainment.EntertainmentFragment
import com.gold.servicenow.food.FoodFragment
import com.gold.servicenow.medicine.MedicineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gold.servicenow.database.DatabaseHandler
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp


class ProfileActivity: ComponentActivity() {
    private lateinit var nameEditLayout: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var emailEditLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var contactEditLayout: TextInputLayout
    private lateinit var contactEditText: TextInputEditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
//            v.updatePadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            WindowInsetsCompat.CONSUMED
//        }
        setContentView(R.layout.activity_profile)

        var nameEdited: Boolean = false
        var emailEdited: Boolean = false
        var contactEdited: Boolean = false
        var passwordEdited: Boolean = false


        nameEditText = findViewById(R.id.profileNameInput)
        emailEditText = findViewById(R.id.profileEmailInput)
        contactEditText = findViewById(R.id.profileNumberInput)
        passwordEditText = findViewById(R.id.profilePasswordInput)
        nameEditLayout = findViewById(R.id.profileName)
        emailEditLayout = findViewById(R.id.profileEmail)
        passwordEditLayout = findViewById(R.id.profilePassword)
        contactEditLayout = findViewById(R.id.profileNumber)
        updateButton = findViewById(R.id.profileUpdateButton)

        println(CurrentProfile.profile?.name)
        println(CurrentProfile.profile?.email)
        println(CurrentProfile.profile?.contact)
        println(CurrentProfile.profile?.password)


        nameEditText.setText(CurrentProfile.profile?.name)
        emailEditText.setText(CurrentProfile.profile?.email)
        passwordEditText.setText(CurrentProfile.profile?.password)
        contactEditText.setText(CurrentProfile.profile?.contact)

        setButtonActivated(updateButton, false)

        nameEditText.addTextChangedListener {
            nameEdited = nameEditText.text.toString() != CurrentProfile.profile?.name
            if (nameEdited || emailEdited || contactEdited || passwordEdited) { setButtonActivated(updateButton, true) }
            else { setButtonActivated(updateButton, false) }
        }

        emailEditText.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            emailEditLayout.helperText = InputValidator.validateEmail(emailEditText.text.toString())
        }

        emailEditText.addTextChangedListener {
            emailEdited = emailEditText.text.toString() != CurrentProfile.profile?.email
            if (nameEdited || emailEdited || contactEdited || passwordEdited) { setButtonActivated(updateButton, true) }
            else { setButtonActivated(updateButton, false) }
        }

        contactEditText.addTextChangedListener {
            contactEdited = contactEditText.text.toString() != CurrentProfile.profile?.contact
            if (nameEdited || emailEdited || contactEdited || passwordEdited) { setButtonActivated(updateButton, true) }
            else { setButtonActivated(updateButton, false) }
        }

        passwordEditText.addTextChangedListener {
            passwordEdited = passwordEditText.text.toString() != CurrentProfile.profile?.password
            if (nameEdited || emailEdited || contactEdited || passwordEdited) { setButtonActivated(updateButton, true) }
            else { setButtonActivated(updateButton, false) }
        }
        passwordEditText.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            passwordEditLayout.helperText = InputValidator.validatePassword(passwordEditText.text.toString())
        }

        updateButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val contact = contactEditText.text.toString()
            val password = passwordEditText.text.toString()
            val newProfile = Profile(name, email, contact, password)
            CurrentProfile.update(newProfile,
                onSuccess = {
                    println("[Profile]: Profile updated successfully.")
                    finish()
                },
                onFailure = {
                    println("[Profile]: Profile update failed.")
                })
            finish()
        }

    }
    private fun setButtonActivated(button: Button, status: Boolean) {
        if (status) {
            button.isEnabled = true
            button.visibility = Button.VISIBLE
        } else {
            button.isEnabled = false
            button.visibility = Button.GONE
        }
    }
}