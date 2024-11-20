package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gold.servicenow.profile.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : ComponentActivity() {
    private lateinit var registerButton: Button
    private lateinit var loginButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var emailEditText: TextInputEditText
    private lateinit var emailEditLayout: TextInputLayout
    private lateinit var nameEditText: EditText
    private lateinit var nameEditLayout: TextInputLayout
    private lateinit var contactEditText: EditText
    private lateinit var contactEditLayout: TextInputLayout
    private lateinit var passwordEditText: EditText
    private lateinit var passwordEditLayout: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        registerButton = findViewById(R.id.registerSignupButton)
        loginButton = findViewById(R.id.registerSignIn)
        backButton = findViewById(R.id.registerBackButton)
        emailEditText = findViewById(R.id.registerEmailInput)
        nameEditText = findViewById(R.id.registerNameInput)
        contactEditText = findViewById(R.id.registerNumberInput)
        passwordEditText = findViewById(R.id.registerPasswordInput)
        emailEditLayout = findViewById(R.id.registerEmail)
        nameEditLayout = findViewById(R.id.registerName)
        contactEditLayout = findViewById(R.id.registerNumber)
        passwordEditLayout = findViewById(R.id.registerPassword)

        emailEditText.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            emailEditLayout.helperText = InputValidator.validateEmail(emailEditText.text.toString())
        }

        passwordEditText.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            passwordEditLayout.helperText = InputValidator.validatePassword(passwordEditText.text.toString())
        }

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val contact = contactEditText.text.toString()
            val password = passwordEditText.text.toString()
            val newProfile = Profile(name, email, contact, password)
            CurrentProfile.register(newProfile,
                onSuccess = {
                    println("[Register]: Registration successful.")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onFailure = {
                    Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show()
                    println("[Register]: Registration failed.")
                })
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}