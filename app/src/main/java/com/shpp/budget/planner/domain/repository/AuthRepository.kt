package com.shpp.budget.planner.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getIsUserLoggedIn(): Flow<Boolean?>
}