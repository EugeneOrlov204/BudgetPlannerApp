package com.shpp.budget.planner.domain.validation


/**
 * this is a password validation pattern
 * that checks if the password contains at least one number,
 * one lowercase and one uppercase letter, and one special character,
 * and the password contains 8 to 16 characters
 */
const val PASSWORD_PATTERN =
    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#\$@!%&*?])[A-Za-z\\d#\$@!%&*?]{8,16}\$"

const val EMAIL_PATTERN = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

const val MAXIMUM_PASSWORD_LENGTH = 16

const val MINIMUM_PASSWORD_LENGTH = 8

/**
 *class for validation of fields during authorization
 */
class AuthValidator {
    /**
     * method that checks if the email is not empty and and matches the email pattern
     * @param email text that will be considered as a email
     * @return aa value of type EmailValidationResult,
     *  which indicates the result of password validation.
     */
    fun validateEmail(email: String): EmailValidationResult {
        return if (email.isBlank())
            EmailValidationResult.BLANK
        else if (!email.matches(Regex(EMAIL_PATTERN)))
            EmailValidationResult.INVALID
        else
            EmailValidationResult.VALID
    }

    /**
     * method that checks if the email is not empty and and matches the password pattern
     * @param password text that will be considered as a password
     * @return a value of type PasswordValidationResult,
     * which indicates the result of password validation.
     */
    fun validatePassword(password: String): PasswordValidationResult {
        return if (password.isBlank())
            PasswordValidationResult.BLANK
        else if (password.length < MINIMUM_PASSWORD_LENGTH || password.length > MAXIMUM_PASSWORD_LENGTH)
            PasswordValidationResult.LENGTH_ERROR
        else if (!password.matches(Regex(PASSWORD_PATTERN)))
            PasswordValidationResult.INVALID
        else PasswordValidationResult.VALID
    }
}