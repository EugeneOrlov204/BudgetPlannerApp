package com.shpp.budget.planner.domain.repository

import com.shpp.budget.planner.data.model.Transaction

interface TransactionsRepository {
    suspend fun addTransaction(userUID: String, transaction: Transaction): Result<Unit>
}