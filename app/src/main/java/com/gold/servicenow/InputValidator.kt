package com.gold.servicenow

import android.util.Patterns

class InputValidator {
    companion object {
        fun validateEmail(email: String): String? {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "Invalid Email Address"
            return null
        }

        fun validatePassword(password: String): String? {
            if (password.length < 8) return "Password must be at least 8 characters long"
            if (!password.matches(".*[a-z].*".toRegex())) return "Password must contain at least one lowercase letter"
            if (!password.matches(".*[A-Z].*".toRegex())) return "Password must contain at least one uppercase letter"
            if (!password.matches(".*[0-9].*".toRegex())) return "Password must contain at least one alphanumeric number"
            return null
        }
    }
}