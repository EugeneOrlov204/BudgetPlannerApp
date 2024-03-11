package com.shpp.budget.planner.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shpp.budget.planner.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor() : AuthRepository {
    private var _isLoggedIn = MutableStateFlow(Firebase.auth.currentUser != null)
    override fun getIsUserLoggedIn(): Flow<Boolean> {
        return _isLoggedIn
    }

    override suspend fun registerUser(email: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                Firebase.auth.createUserWithEmailAndPassword(email, password).await()
                _isLoggedIn.update {
                    Firebase.auth.currentUser != null
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                Firebase.auth.signInWithEmailAndPassword(email, password).await()
                _isLoggedIn.update {
                    Firebase.auth.currentUser != null
                }
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}