package com.gold.servicenow.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.gold.servicenow.InputValidator
import com.gold.servicenow.R
import com.gold.servicenow.databinding.ActivityProfileBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp


class ProfileActivity: ComponentActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
//            v.updatePadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            WindowInsetsCompat.CONSUMED
//        }
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var nameEdited: Boolean = false
        var emailEdited: Boolean = false
        var contactEdited: Boolean = false
        var passwordEdited: Boolean = false
        var imageEdited: Boolean = false

        binding.profileNameInput.setText(CurrentProfile.profile?.name)
        binding.profileEmailInput.setText(CurrentProfile.profile?.email)
        binding.profilePasswordInput.setText(CurrentProfile.profile?.password)
        binding.profileNumberInput.setText(CurrentProfile.profile?.contact)

        setButtonActivated(binding.profileUpdateButton, false)


        binding.profileNameInput.addTextChangedListener {
            nameEdited = binding.profileNameInput.text.toString() != CurrentProfile.profile?.name
            if (nameEdited || emailEdited || contactEdited || passwordEdited || imageEdited) { setButtonActivated(binding.profileUpdateButton, true) }
            else { setButtonActivated(binding.profileUpdateButton, false) }
        }

        binding.profileEmailInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.profileEmail.helperText = InputValidator.validateEmail(binding.profileEmailInput.text.toString())
        }

        binding.profileEmailInput.addTextChangedListener {
            emailEdited = binding.profileEmailInput.text.toString() != CurrentProfile.profile?.email
            if (nameEdited || emailEdited || contactEdited || passwordEdited || imageEdited) { setButtonActivated(binding.profileUpdateButton, true) }
            else { setButtonActivated(binding.profileUpdateButton, false) }
        }

        binding.profileNumberInput.addTextChangedListener {
            contactEdited = binding.profileNumberInput.text.toString() != CurrentProfile.profile?.contact
            if (nameEdited || emailEdited || contactEdited || passwordEdited || imageEdited) { setButtonActivated(binding.profileUpdateButton, true) }
            else { setButtonActivated(binding.profileUpdateButton, false) }
        }

        binding.profilePasswordInput.addTextChangedListener {
            passwordEdited = binding.profilePasswordInput.text.toString() != CurrentProfile.profile?.password
            if (nameEdited || emailEdited || contactEdited || passwordEdited || imageEdited) { setButtonActivated(binding.profileUpdateButton, true) }
            else { setButtonActivated(binding.profileUpdateButton, false) }
        }
        binding.profilePasswordInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.profilePassword.helperText = InputValidator.validatePassword(binding.profilePasswordInput.text.toString())
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