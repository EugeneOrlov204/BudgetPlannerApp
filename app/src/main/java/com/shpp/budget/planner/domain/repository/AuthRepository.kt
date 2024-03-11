package com.shpp.budget.planner.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getIsUserLoggedIn(): Flow<Boolean>
    suspend fun registerUser(email: String, password: String): Result<Unit>
    suspend fun loginUser(email: String, password: String): Result<Unit>
}