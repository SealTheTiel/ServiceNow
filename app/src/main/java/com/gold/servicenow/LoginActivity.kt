package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import com.gold.servicenow.databinding.ActivityLoginBinding
import com.gold.servicenow.profile.CurrentProfile
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.loginEmailInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.loginEmail.helperText = InputValidator.validateEmail(binding.loginEmailInput.text.toString())

        }

        binding.loginPasswordInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.loginPassword.helperText = InputValidator.validatePassword(binding.loginPasswordInput.text.toString())
        }


        binding.loginLoginButton.setOnClickListener {
            // Get the input values
            val email = binding.loginEmailInput.text.toString().trim()
            val password = binding.loginPasswordInput.text.toString().trim()

            // Perform validation checks
            var isValid = true

            // Validate email
            val emailError = InputValidator.validateEmail(email)
            if (emailError != null) {
                binding.loginEmail.helperText = emailError
                isValid = false
            } else {
                binding.loginEmail.helperText = null
            }

            // Validate password
            val passwordError = InputValidator.validatePassword(password)
            if (passwordError != null) {
                binding.loginPassword.helperText = passwordError
                isValid = false
            } else {
                binding.loginPassword.helperText = null
            }

            // Validate email and password for blank inputs
            if (email.isBlank()) {
                binding.loginEmail.helperText = "Email cannot be empty"
                isValid = false
            }

            if (password.isBlank()) {
                binding.loginPassword.helperText = "Password cannot be empty"
                isValid = false
            }

            // If validation fails, stop the process and show a toast
            if (!isValid) {
                Toast.makeText(this, "Please fill out all fields correctly.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Proceed with login if all fields are valid
            binding.loginLoginButton.isEnabled = false
            binding.loginLoginButton.visibility = View.INVISIBLE
            binding.loginLoading.visibility = View.VISIBLE

            CurrentProfile.login(email, password,
                onSuccess = { msg ->
                    Log.i("Log In", msg.toString())
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onFailure = { msg ->
                    Log.e("Log In", msg.toString())
                    Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                    binding.loginLoginButton.isEnabled = true
                    binding.loginLoginButton.visibility = View.VISIBLE
                    binding.loginLoading.visibility = View.INVISIBLE
                }
            )
        }

        binding.loginSignup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }

        binding.loginBackButton.setOnClickListener {
            finish()
        }
    }
}