package com.shpp.budget.planner.data.repository

import com.shpp.budget.planner.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor() : AuthRepository {
    override fun getIsUserLoggedIn(): Flow<Boolean?> = flow {
        emit(null)
        delay(2000)
        emit(false)
    }
}