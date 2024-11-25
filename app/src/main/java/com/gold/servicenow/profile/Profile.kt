package com.gold.servicenow.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.InputStream

class Profile {
    var name: String = ""
        private set
    var email: String = ""
        private set
    var contact: String = ""
        private set
    var password: String = ""
        private set
    var image: String = ""
        private set

    constructor(name: String, email: String, contact: String, password: String, image: String) {
        this.name = name
        this.email = email
        this.contact = contact
        this.password = password
        this.image = image
    }

    constructor(name: String, email: String, contact: String, password: String, image: Bitmap) {
        this.name = name
        this.email = email
        this.contact = contact
        this.password = password
        this.image = CurrentProfile.convertBitmapToBase64(image)
    }


    constructor(name: String, email: String, contact: String, password: String) {
        this.name = name
        this.email = email
        this.contact = contact
        this.password = password
    }
}

object CurrentProfile {
    var profile: Profile? = null
    fun login(email: String, password: String, onSuccess: (Any?) -> Unit, onFailure: (Any?) -> Unit) {
        ProfileDatabase().getProfile(email, password,
            onSuccess = { profileFromDb, msg -> this.profile = profileFromDb
                            onSuccess(msg)
                            return@getProfile
                        },
            onFailure = { msg -> onFailure(msg)
                            return@getProfile
                        }
        )
    }

    fun register(profile: Profile, onSuccess: (Any?) -> Unit, onFailure: (Any?) -> Unit) {
        ProfileDatabase().insertProfile(profile,
            onSuccess = {   newProfile, msg -> this.profile = newProfile
                            onSuccess(msg)
                            return@insertProfile
                        },
            onFailure = {   msg -> onFailure(msg)
                            return@insertProfile
                        }
        )
    }

    fun update(newProfile: Profile, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        ProfileDatabase().updateUser(this.profile!!, newProfile,
            onSuccess = {   this.profile = newProfile
                            onSuccess()
                        },
            onFailure = {   Exception -> onFailure(Exception)
                            println("[ERROR] [Profile] Failed to update profile: ${this.profile!!.name}")
                        }
        )
    }

    fun convertUriToBitmap (context: Context, uri: Uri) : Bitmap? {
        return try {
            val inputStream: InputStream = context.contentResolver.openInputStream(uri)!!
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            println("[ERROR] [Profile]: Failed to convert Uri to Bitmap.")
            null
        }
    }

    fun convertBitmapToBase64 (bitmap: Bitmap?) : String {
        if (bitmap == null) return ""
        return try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        } catch (e: Exception) {
            println("[ERROR] [Profile]: Failed to convert Uri to Base64.")
            ""
        }
    }

    fun convertBase64ToBitmap (base64: String) : Bitmap? {
        return try {
            val byteArray: ByteArray = Base64.decode(base64, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        } catch (e: Exception) {
            println("[ERROR] [Profile]: Failed to convert Base64 to Bitmap.")
            null
        }
    }
}