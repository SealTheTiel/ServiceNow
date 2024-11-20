package com.gold.servicenow.profile


import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.gold.servicenow.database.*
import com.gold.servicenow.database.DatabaseHandler
import com.gold.servicenow.database.DatabaseHandler.Companion.PROFILE_COLLECTION
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlin.math.max


class ProfileDatabase {
    private val firestore = DatabaseHandler.firestore
    fun insertProfile(profile: Profile, onSuccess: (Profile) -> Unit, onFailure: (Exception) -> Unit) {
        val profileMap = mapOf(
            "name" to profile.name,
            "email" to profile.email,
            "contact" to profile.contact,
            "password" to profile.password,
           "imageUrl" to profile.imageUrl
        )

        firestore.collection(PROFILE_COLLECTION)
            .get()
            .addOnSuccessListener { results ->
                for (result in results) {
                    if (result.get("email") == profile.email) {
                        onFailure(Exception("Email already exists"))
                        return@addOnSuccessListener
                    }
                }
                firestore.collection(PROFILE_COLLECTION)
                    .add(profileMap)
                    .addOnSuccessListener {
                        onSuccess(profile)
                    }
                    .addOnFailureListener {
                            exception -> onFailure(exception)
                    }
            }
            .addOnFailureListener {
                exception -> onFailure(exception)
            }
    }

    fun getProfile(email: String, password: String, onSuccess: (Profile) -> Unit, onFailure: (Exception) -> Unit) {
        lateinit var profile: Profile
        firestore.collection(PROFILE_COLLECTION)
            .get()
            .addOnSuccessListener { results ->
                for (result in results) {
                    if (result.get("email") == email && result.get("password") == password) {
                        profile = Profile(
                            result.get("name") as String,
                            result.get("email") as String,
                            result.get("contact") as String,
                            result.get("password") as String,
                            result.get("imageUrl") as String
                        )
                        onSuccess(profile)
                    }
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun updateUser(currentProfile: Profile, newProfile: Profile, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val profileMap = mapOf(
            "name" to newProfile.name,
            "email" to newProfile.email,
            "contact" to newProfile.contact,
            "password" to newProfile.password,
            "imageUrl" to newProfile.imageUrl
        )
        firestore.collection(PROFILE_COLLECTION)
            .get()
            .addOnSuccessListener {
                for (result in it) {
                    if (result.get("email") == currentProfile.email) {
                        firestore.collection(PROFILE_COLLECTION)
                            .document(result.id)
                            .update(profileMap)
                            .addOnSuccessListener {
                                onSuccess()
                            }
                            .addOnFailureListener {
                                exception -> onFailure(exception)
                            }
                    }
                }
            }
            .addOnFailureListener {
                exception -> onFailure(exception)
            }
    }

}