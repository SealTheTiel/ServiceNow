package com.gold.servicenow.profile


import com.gold.servicenow.database.DatabaseHandler
import com.gold.servicenow.database.DatabaseHandler.Companion.PROFILE_COLLECTION


class ProfileDatabase {
    private val firestore = DatabaseHandler.firestore
    fun insertProfile(profile: Profile, onSuccess: (profile: Profile, Any?) -> Unit, onFailure: (Any?) -> Unit) {
        val profileMap = mapOf(
            "name" to profile.name,
            "email" to profile.email,
            "contact" to profile.contact,
            "password" to profile.password,
            "image" to profile.image
        )
        firestore.collection(PROFILE_COLLECTION)
            .get()
            .addOnSuccessListener { results ->
                for (result in results) {
                    if (result.get("email") == profile.email) {
                        onFailure("Email \"${profile.email}\" is already taken.")
                        return@addOnSuccessListener
                    }
                }
                firestore.collection(PROFILE_COLLECTION)
                    .add(profileMap)
                    .addOnSuccessListener {
                        onSuccess(profile, "Successfully registered \"${profile.email}\"")
                    }
                    .addOnFailureListener {
                        onFailure("Error registering account with email: \"${profile.email}\"")
                    }
            }
            .addOnFailureListener {
                onFailure("Error registering account with email: \"${profile.email}\"")
            }
    }

    fun getProfile(email: String, password: String, onSuccess: (profile: Profile, Any?) -> Unit, onFailure: (Any?) -> Unit) {
        lateinit var profileFromDb: Profile
        firestore.collection(PROFILE_COLLECTION)
            .get()
            .addOnSuccessListener { results ->
                for (result in results) {
                    if (result.get("email") == email && result.get("password") == password) {
                        profileFromDb = Profile(
                            result.get("name") as String,
                            result.get("email") as String,
                            result.get("contact") as String,
                            result.get("password") as String,
                            result.get("image") as String
                        )
                        onSuccess(profileFromDb, "Account with email \"$email\" found.")
                        return@addOnSuccessListener
                    }
                }
                onFailure("Account with email \"$email\" not found.")
                return@addOnSuccessListener
            }
            .addOnFailureListener {
                onFailure("Account with email \"$email\" not found.")
            }
    }

    fun updateUser(currentProfile: Profile, newProfile: Profile, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val profileMap = mapOf(
            "name" to newProfile.name,
            "email" to newProfile.email,
            "contact" to newProfile.contact,
            "password" to newProfile.password,
            "image" to newProfile.image
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