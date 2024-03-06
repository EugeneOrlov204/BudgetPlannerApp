package com.shpp.budget.planner.presentation.signInScreen

data class SignInState(
    val isLoading: Boolean = false,
    val state: Boolean = false,
    val error: String? = null
)
