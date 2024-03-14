package com.shpp.budget.planner.domain.validation

import androidx.core.util.PatternsCompat
import com.shpp.budget.planner.R

/**
 *class for validation of fields during authorization
 */
class AuthValidator {
    /**
     * this is a password validation pattern
     * that checks if the password contains at least one number,
     * one lowercase and one uppercase letter, and one special character,
     * and the password contains 8 to 16 characters
     */
    private val passwordPattern =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#\$@!%&*?])[A-Za-z\\d#\$@!%&*?]{8,16}\$"

    /**
     * method that checks if the username is not empty
     * @param username text that will be considered as a username
     * @return a class with a boolean field with the validation result
     * and a string resource id with the validation result message
     */
    fun validateUsername(username: String): ValidationResult {
        return if (username.isBlank())
            ValidationResult(false, R.string.empty_field_validation_error)
        else ValidationResult(true, R.string.validation_success)
    }

    /**
     * method that checks if the email is not empty and and matches the email pattern
     * @param email text that will be considered as a email
     * @return a class with a boolean field with the validation result
     * and a string resource id with the validation result message
     */
    fun validateEmail(email: String): ValidationResult {
        return if (email.isBlank())
            ValidationResult(false, R.string.empty_field_validation_error)
        else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches())
            ValidationResult(false, R.string.email_validation_incorrect_email)
        else
            ValidationResult(true, R.string.validation_success)
    }

    /**
     * method that checks if the email is not empty and and matches the password pattern
     * @param password text that will be considered as a password
     * @return a class with a boolean field with the validation result
     * and a string resource id with the validation result message
     */
    fun validatePassword(password: String): ValidationResult {
        return if (password.isBlank())
            ValidationResult(false, R.string.empty_field_validation_error)
        else if (!password.matches(Regex(passwordPattern)))
            ValidationResult(false, R.string.password_validation_error)
        else ValidationResult(true, R.string.validation_success)
    }

}