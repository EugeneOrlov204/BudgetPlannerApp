package com.shpp.budget.planner.domain.useCases.transactions

import com.shpp.budget.planner.domain.repository.AuthRepository
import com.shpp.budget.planner.domain.repository.TransactionsRepository
import javax.inject.Inject

class GetIncomesUseCase @Inject constructor(
    private val repository: TransactionsRepository,
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() =
        authRepository.userUID.value?.let {
            repository.getIncomes(it)
        }

}