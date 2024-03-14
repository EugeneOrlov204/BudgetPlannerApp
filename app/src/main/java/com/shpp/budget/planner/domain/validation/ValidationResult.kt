package com.shpp.budget.planner.domain.validation

/**
 * the class that shows the validation results,
 * @property isValid field is responsible for the validation result,
 * @property message field contains the string resource ID with a message about the validation result
 */
data class ValidationResult(
    val isValid: Boolean,
    val message: Int
)
