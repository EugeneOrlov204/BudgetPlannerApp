package com.shpp.budget.planner.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val isLoggedIn: StateFlow<Boolean?>
    suspend fun registerUser(email: String, password: String): Result<Unit>
    suspend fun loginUser(email: String, password: String): Result<Unit>
}