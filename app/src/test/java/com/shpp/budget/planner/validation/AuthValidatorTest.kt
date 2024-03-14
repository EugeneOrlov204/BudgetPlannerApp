package com.shpp.budget.planner.validation

import com.shpp.budget.planner.domain.validation.AuthValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class AuthValidatorTest {

    private lateinit var authValidator: AuthValidator

    @Before
    fun setUp() {
        authValidator = AuthValidator()
    }

    @Test
    fun validateUsername_blankUsername_returnsFalse() {
        // Arrange
        val username = ""

        // Act
        val result = authValidator.validateUsername(username)

        // Assert
        assertFalse(result.isValid)
    }

    @Test
    fun validateUsername_nonBlankUsername_returnsTrue() {
        // Arrange
        val username = "testuser"

        // Act
        val result = authValidator.validateUsername(username)

        // Assert
        assertTrue(result.isValid)
    }

    @Test
    fun validateEmail_blankEmail_returnsFalse() {
        // Arrange
        val email = ""

        // Act
        val result = authValidator.validateEmail(email)

        // Assert
        assertFalse(result.isValid)
    }

    @Test
    fun validateEmail_invalidEmail_returnsFalse() {
        // Arrange
        val email = "invalidemail"

        // Act
        val result = authValidator.validateEmail(email)

        // Assert
        assertFalse(result.isValid)
    }

    @Test
    fun validateEmail_validEmail_returnsTrue() {
        // Arrange
        val email = "validemail@example.com"

        // Act
        val result = authValidator.validateEmail(email)

        // Assert
        assertTrue(result.isValid)
    }

    @Test
    fun validatePassword_blankPassword_returnsFalse() {
        // Arrange
        val password = ""

        // Act
        val result = authValidator.validatePassword(password)

        // Assert
        assertFalse(result.isValid)
    }

    @Test
    fun validatePassword_invalidPassword_returnsFalse() {
        // Arrange
        val password = "invalidpassword"

        // Act
        val result = authValidator.validatePassword(password)

        // Assert
        assertFalse(result.isValid)
    }

    @Test
    fun validatePassword_invalidLongPassword_returnsFalse() {
        // Arrange
        val password = "inValidPassword123$"

        // Act
        val result = authValidator.validatePassword(password)

        // Assert
        assertFalse(result.isValid)
    }

    @Test
    fun validatePassword_invalidShortPassword_returnsFalse() {
        // Arrange
        val password = "iF123$"

        // Act
        val result = authValidator.validatePassword(password)

        // Assert
        assertFalse(result.isValid)
    }

    @Test
    fun validatePassword_validPassword_returnsTrue() {
        // Arrange
        val password = "Valid123$"

        // Act
        val result = authValidator.validatePassword(password)

        // Assert
        assertTrue(result.isValid)
    }
}