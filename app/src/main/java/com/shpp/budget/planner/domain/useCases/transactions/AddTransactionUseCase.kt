package com.shpp.budget.planner.domain.useCases.transactions

import com.shpp.budget.planner.data.model.Transaction
import com.shpp.budget.planner.domain.repository.AuthRepository
import com.shpp.budget.planner.domain.repository.TransactionsRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(transaction: Transaction) = authRepository.userUID.value?.let {
        transactionsRepository.addTransaction(it, transaction)
    }
}