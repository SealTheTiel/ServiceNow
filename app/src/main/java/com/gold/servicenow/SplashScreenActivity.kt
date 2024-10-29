package com.gold.servicenow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Button


class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}