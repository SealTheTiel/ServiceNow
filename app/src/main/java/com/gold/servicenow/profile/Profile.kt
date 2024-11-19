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