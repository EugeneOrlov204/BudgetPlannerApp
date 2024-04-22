package com.shpp.budget.planner.domain.useCases.transactions

import com.shpp.budget.planner.domain.repository.AuthRepository
import com.shpp.budget.planner.domain.repository.TransactionsRepository
import javax.inject.Inject

class GetBudgetUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() =
        authRepository.userUID.value?.let {
            transactionsRepository.getIncomeSum(it)
                .getOrDefault(0.0) - transactionsRepository.getExpenseSum(it).getOrDefault(0.0)
        }

}