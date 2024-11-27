package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.gold.servicenow.databinding.ActivityRegisterBinding
import com.gold.servicenow.profile.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.registerEmailInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.registerEmail.helperText = InputValidator.validateEmail(binding.registerEmailInput.text.toString())
        }

        binding.registerPasswordInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.registerPassword.helperText = InputValidator.validatePassword(binding.registerPasswordInput.text.toString())
        }

        binding.registerSignupButton.setOnClickListener {
            // Get the input values
            val name = binding.registerNameInput.text.toString().trim()
            val email = binding.registerEmailInput.text.toString().trim()
            val contact = binding.registerNumberInput.text.toString().trim()
            val password = binding.registerPasswordInput.text.toString().trim()

            // Perform validation checks
            var isValid = true

            // Validate name (check if it's blank)
            if (name.isBlank()) {
                binding.registerName.helperText = "Name cannot be empty"
                isValid = false
            } else {
                binding.registerName.helperText = null
            }

            // Validate email
            val emailError = InputValidator.validateEmail(email)
            if (emailError != null) {
                binding.registerEmail.helperText = emailError
                isValid = false
            } else {
                binding.registerEmail.helperText = null
            }

            // Validate contact (check if it's blank)
            if (contact.isBlank()) {
                binding.registerNumber.helperText = "Contact number cannot be empty"
                isValid = false
            } else {
                binding.registerNumber.helperText = null
            }

            // Validate password
            val passwordError = InputValidator.validatePassword(password)
            if (passwordError != null) {
                binding.registerPassword.helperText = passwordError
                isValid = false
            } else {
                binding.registerPassword.helperText = null
            }

            // If any validation fails, show a message and stop the process
            if (!isValid) {
                Toast.makeText(this, "Please fill all fields correctly.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Proceed with registration if all fields are valid
            binding.registerSignupButton.isEnabled = false
            binding.registerSignupButton.visibility = View.INVISIBLE
            binding.registerLoading.visibility = View.VISIBLE

            val newProfile = Profile(name, email, contact, password)
            CurrentProfile.register(newProfile,
                onSuccess = { msg ->
                    Log.i("Register", msg.toString())
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onFailure = { msg ->
                    Log.e("Register", msg.toString())
                    Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show()
                    println("[Register]: Registration failed.")
                    binding.registerSignupButton.isEnabled = true
                    binding.registerSignupButton.visibility = View.VISIBLE
                    binding.registerLoading.visibility = View.INVISIBLE
                })
        }


        binding.registerSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        binding.registerBackButton.setOnClickListener {
            finish()
        }
    }
}