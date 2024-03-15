package com.shpp.budget.planner.domain.useCases.auth

import com.shpp.budget.planner.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserLoginStatusUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.isLoggedIn
}