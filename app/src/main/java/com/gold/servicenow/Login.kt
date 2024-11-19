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
import com.gold.servicenow.database.DatabaseHandler
import com.gold.servicenow.profile.CurrentProfile

class Login : ComponentActivity() {
    private lateinit var loginButton: Button
    private lateinit var signupButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

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
                }
            )
        }
        signupButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}