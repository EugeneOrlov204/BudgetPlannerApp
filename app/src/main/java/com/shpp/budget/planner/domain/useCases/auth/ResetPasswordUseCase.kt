package com.shpp.budget.planner.domain.useCases.auth

import com.shpp.budget.planner.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String) = repository.resetPassword(email)
}