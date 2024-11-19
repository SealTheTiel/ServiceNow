package com.gold.servicenow.profile

import com.google.android.gms.tasks.Tasks.await

class Profile(name: String, email: String, contact: String, password: String, imageUrl: String) {
    var name = name
        private set

    var email = email
        private set

    var contact = contact
        private set

    var password = password
        private set

    var imageUrl = imageUrl
        private set

    constructor(name: String, email: String, contact: String, password: String) : this(name, email, contact, password, "")
}

object CurrentProfile {
    var profile: Profile? = null
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        ProfileDatabase().getProfile(email, password,
            onSuccess = {   profile -> this.profile = profile
                            onSuccess()
                        },
            onFailure = {   Exception -> onFailure(Exception)
                            println("[ERROR] [Login] Failed to login with email: $email")
                        }
        )
    }

    fun register(profile: Profile, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        ProfileDatabase().insertProfile(profile,
            onSuccess = {   profile -> this.profile = profile
                            onSuccess()
                        },
            onFailure = {   Exception -> onFailure(Exception)
                            println("[ERROR] [Register] Failed to register profile: ${profile.name}")
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
}