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
import kotlin.math.log

class Register : ComponentActivity() {
    private lateinit var registerButton: Button
    private lateinit var googleButton: Button
    private lateinit var loginButton: TextView
    private lateinit var backButton: ImageButton
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
        googleButton = findViewById(R.id.registerGoogleButton)
        loginButton = findViewById(R.id.registerSignIn)
        backButton = findViewById(R.id.registerBackButton)

        registerButton.setOnClickListener {
            registerButton.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.button_click))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        googleButton.setOnClickListener {
            googleButton.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.button_click))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginButton.setOnClickListener {
            loginButton.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, R.anim.button_click))
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}