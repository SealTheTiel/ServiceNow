package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
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


        binding.loginLoginButton.setOnClickListener {
            binding.loginLoginButton.isEnabled = false
            binding.loginLoginButton.visibility = View.INVISIBLE
            binding.loginLoading.visibility = View.VISIBLE
            val email = binding.loginEmailInput.text.toString()
            val password = binding.loginPasswordInput.text.toString()
            CurrentProfile.login(email, password,
                onSuccess = {
                    println("[INFO] [Login]: Log in successful with email: $email")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onFailure = {
                    println("[ERROR] [Login]: Failed to log in with email: $email")
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