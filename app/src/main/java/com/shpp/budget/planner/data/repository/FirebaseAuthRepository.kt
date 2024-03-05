package com.shpp.budget.planner.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shpp.budget.planner.domain.repository.AuthRepository
import com.shpp.budget.planner.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor() : AuthRepository {
    override fun getIsUserLoggedIn(): Flow<Boolean> = flow {
        emit(Firebase.auth.currentUser != null)
    }

    override suspend fun registerUser(email: String, password: String): Flow<Resource<Unit>> {
        return flow {
            try {
                emit(Resource.Loading(true))
                Firebase.auth.createUserWithEmailAndPassword(email, password).await()
                Firebase.auth.currentUser
                emit(Resource.Success())
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }

}