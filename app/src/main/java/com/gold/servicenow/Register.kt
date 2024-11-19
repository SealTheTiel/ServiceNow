package com.gold.servicenow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.gold.servicenow.profile.*
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class Register : ComponentActivity() {
    private lateinit var registerButton: Button
    private lateinit var loginButton: TextView
    private lateinit var backButton: ImageButton
    private lateinit var emailEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var contactEditText: EditText
    private lateinit var passwordEditText: EditText


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
                    println("[Register]: Registration failed.")
                })
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}