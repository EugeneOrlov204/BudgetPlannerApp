package com.shpp.budget.planner.data.repository

import android.util.Log
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.shpp.budget.planner.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

const val AUTH_LOG_TAG = "FirebaseAuthLogTag"

@Singleton
class FirebaseAuthRepository @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override val isLoggedIn = MutableStateFlow<Boolean?>(null)

    init {
        auth.addAuthStateListener { firebaseAuth ->
            isLoggedIn.update {
                firebaseAuth.currentUser != null
            }
        }
    }

    override suspend fun registerUser(email: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                Result.success(Unit)
            } catch (e: FirebaseAuthException) {
                Log.e(AUTH_LOG_TAG, "Error creating user: ${e.message}")
                Result.failure(e)
            } catch (e: IllegalArgumentException) {
                Log.e(AUTH_LOG_TAG, "Error in email or password: ${e.message}")
                Result.failure(e)
            } catch (e: FirebaseTooManyRequestsException) {
                Result.failure(e)
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                Result.success(Unit)
            } catch (e: FirebaseAuthException) {
                Log.e(AUTH_LOG_TAG, "Error logging user in: ${e.message}")
                Result.failure(e)
            } catch (e: IllegalArgumentException) {
                Log.e(AUTH_LOG_TAG, "Error in email or password: ${e.message}")
                Result.failure(e)
            } catch (e: FirebaseTooManyRequestsException) {
                Result.failure(e)
            }
        }
    }

}