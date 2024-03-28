package com.shpp.budget.planner.domain.validation


import junit.framework.TestCase.assertEquals

import org.junit.Before
import org.junit.Test


class AuthValidatorTest {

    private lateinit var authValidator: AuthValidator

    @Before
    fun setUp() {
        authValidator = AuthValidator()
    }

    @Test
    fun validateEmail_blankEmail_returnsBlankResult() {
        val result = authValidator.validateEmail("")
        assertEquals(EmailValidationResult.BLANK, result)
    }

    @Test
    fun validateEmail_invalidEmail_returnsInvalidResult() {
        val result = authValidator.validateEmail("invalid_email")
        assertEquals(EmailValidationResult.INVALID, result)
    }

    @Test
    fun validateEmail_validEmail_returnsValidResult() {
        val result = authValidator.validateEmail("valid_email@example.com")
        assertEquals(EmailValidationResult.VALID, result)
    }

    @Test
    fun validatePassword_blankPassword_returnsBlankResult() {
        val result = authValidator.validatePassword("")
        assertEquals(PasswordValidationResult.BLANK, result)
    }

    @Test
    fun validatePassword_shortPassword_returnsLengthErrorResult() {
        val result = authValidator.validatePassword("short")
        assertEquals(PasswordValidationResult.LENGTH_ERROR, result)
    }

    @Test
    fun validatePassword_longPassword_returnsLengthErrorResult() {
        val result = authValidator.validatePassword("this_is_a_very_long_password")
        assertEquals(PasswordValidationResult.LENGTH_ERROR, result)
    }

    @Test
    fun validatePassword_invalidPassword_returnsInvalidResult() {
        val result = authValidator.validatePassword("invalidpassword")
        assertEquals(PasswordValidationResult.INVALID, result)
    }

    @Test
    fun validatePassword_validPassword_returnsValidResult() {
        val result = authValidator.validatePassword("Password123#")
        assertEquals(PasswordValidationResult.VALID, result)
    }
}

