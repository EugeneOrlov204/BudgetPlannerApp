package com.shpp.budget.planner.domain.repository

import com.shpp.budget.planner.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getIsUserLoggedIn(): Flow<Boolean>
    suspend fun registerUser(email: String, password: String): Flow<Resource<Unit>>
}