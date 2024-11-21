package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Toast
import com.gold.servicenow.profile.CurrentProfile
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : ComponentActivity() {
    private lateinit var loginButton: Button
    private lateinit var signupButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var emailEditLayout: TextInputLayout
    private lateinit var passwordEditLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginButton = findViewById(R.id.loginLoginButton)
        signupButton = findViewById(R.id.loginSignup)
        backButton = findViewById(R.id.loginBackButton)
        emailEditText = findViewById(R.id.loginEmailInput)
        passwordEditText = findViewById(R.id.loginPasswordInput)
        emailEditLayout = findViewById(R.id.loginEmail)
        passwordEditLayout = findViewById(R.id.loginPassword)

        emailEditText.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            emailEditLayout.helperText = InputValidator.validateEmail(emailEditText.text.toString())
        }


        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
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
                }
            )
        }
        signupButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}