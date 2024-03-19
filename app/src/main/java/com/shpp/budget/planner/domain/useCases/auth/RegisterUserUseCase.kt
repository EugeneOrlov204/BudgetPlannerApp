package com.shpp.budget.planner.domain.useCases.auth

import com.shpp.budget.planner.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = authRepository.registerUser(email, password)

}