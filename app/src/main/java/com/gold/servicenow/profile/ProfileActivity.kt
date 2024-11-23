package com.gold.servicenow.profile

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.core.widget.addTextChangedListener
import com.gold.servicenow.InputValidator
import com.gold.servicenow.databinding.ActivityProfileBinding
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class ProfileActivity: ComponentActivity() {
    private lateinit var binding: ActivityProfileBinding
    private var nameEdited: Boolean = false
    private var emailEdited: Boolean = false
    private var contactEdited: Boolean = false
    private var passwordEdited: Boolean = false
    private var imageEdited: Boolean = false

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
        var imageUri: Uri? = null
        binding.profileNameInput.setText(CurrentProfile.profile?.name)
        binding.profileEmailInput.setText(CurrentProfile.profile?.email)
        binding.profilePasswordInput.setText(CurrentProfile.profile?.password)
        binding.profileNumberInput.setText(CurrentProfile.profile?.contact)
        if (CurrentProfile.profile?.image != "") {
            binding.profileImageView.setImageBitmap(CurrentProfile.convertBase64ToBitmap(CurrentProfile.profile?.image!!))
        }
        setButtonActivated(binding.profileUpdateButton, false)

        val photoPicker: ActivityResultLauncher<PickVisualMediaRequest> = registerForActivityResult(PickVisualMedia()) { uri ->
            if (uri == null) { return@registerForActivityResult }
            binding.profileImageView.setImageURI(uri)
            if (uri.path?.length!! > 1048576) { // 1 MiB = 2^20 bytes = 1048576 bytes
                return@registerForActivityResult
            }
            imageUri = uri
            imageEdited = true
            updateButton()
        }

        binding.profileNameInput.addTextChangedListener {
            nameEdited = binding.profileNameInput.text.toString() != CurrentProfile.profile?.name
            updateButton()
        }

        binding.profileEmailInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.profileEmail.helperText = InputValidator.validateEmail(binding.profileEmailInput.text.toString())
        }

        binding.profileEmailInput.addTextChangedListener {
            emailEdited = binding.profileEmailInput.text.toString() != CurrentProfile.profile?.email
            updateButton()
        }

        binding.profileNumberInput.addTextChangedListener {
            contactEdited = binding.profileNumberInput.text.toString() != CurrentProfile.profile?.contact
            updateButton()
        }

        binding.profilePasswordInput.addTextChangedListener {
            passwordEdited = binding.profilePasswordInput.text.toString() != CurrentProfile.profile?.password
            updateButton()
        }
        binding.profilePasswordInput.setOnFocusChangeListener { view, focused ->
            if (focused) return@setOnFocusChangeListener
            binding.profilePassword.helperText = InputValidator.validatePassword(binding.profilePasswordInput.text.toString())
        }

        binding.profileImage.setOnClickListener {
            val oldImageUri = imageUri
            photoPicker.launch(PickVisualMediaRequest.Builder().setMediaType(PickVisualMedia.ImageOnly).build())
            if (oldImageUri == imageUri) {
                Toast.makeText(this, "Image ", Toast.LENGTH_SHORT).show()
            }
            updateButton()
        }

        binding.profileUpdateButton.setOnClickListener {
            val name = binding.profileNameInput.text.toString()
            val email = binding.profileEmailInput.text.toString()
            val contact = binding.profileNumberInput.text.toString()
            val password = binding.profilePasswordInput.text.toString()
            val image = imageUri?.let { CurrentProfile.convertBitmapToBase64(CurrentProfile.convertUriToBitmap(this, it)) } ?: CurrentProfile.profile?.image.orEmpty()

            val newProfile = Profile(name, email, contact, password, image)
            println("name: ${newProfile.name}, email: ${newProfile.email}, contact: ${newProfile.contact}, password: ${newProfile.password}, image: ${newProfile.image}")


            CurrentProfile.update(newProfile,
                onSuccess = {
                    println("[Profile]: Profile updated successfully.")
                    val intent = Intent("com.gold.servicenow.PROFILE_UPDATED")
                    intent.putExtra("profileImage", image)
                    intent.putExtra("profileName", name)
                    intent.putExtra("profileEmail", email)
                    intent.putExtra("profileContact", contact)
                    intent.putExtra("profilePassword", password)
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

                    finish()
                },
                onFailure = {
                    println("[Profile]: Profile update failed.")
                })
        }

        binding.profileBackButton.setOnClickListener {
            finish()
        }
    }
    fun updateButton() {
        if (nameEdited || emailEdited || contactEdited || passwordEdited || imageEdited) { setButtonActivated(binding.profileUpdateButton, true) }
        else { setButtonActivated(binding.profileUpdateButton, false) }
    }

    fun setButtonActivated(button: Button, status: Boolean) {
       if (status) {
           button.isEnabled = true
           button.visibility = Button.VISIBLE
       } else {
           button.isEnabled = false
           button.visibility = Button.GONE
       }
    }

}