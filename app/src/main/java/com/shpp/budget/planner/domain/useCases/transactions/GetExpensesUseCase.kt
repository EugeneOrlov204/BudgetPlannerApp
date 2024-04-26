package com.shpp.budget.planner.domain.useCases.transactions

import com.shpp.budget.planner.domain.repository.AuthRepository
import com.shpp.budget.planner.domain.repository.TransactionsRepository
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() =
        authRepository.userUID.value?.let {
            transactionsRepository.getExpenses(it)
        }

}