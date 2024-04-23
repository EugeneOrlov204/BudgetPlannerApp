package com.shpp.budget.planner.domain.repository

import com.shpp.budget.planner.data.model.Transaction

interface TransactionsRepository {
    suspend fun addTransaction(userUID: String, transaction: Transaction): Result<Unit>
    suspend fun getExpenses(userUID: String): Result<List<Transaction.Expense>>
    suspend fun getIncomes(userUID: String): Result<List<Transaction.Income>>
    suspend fun getIncomeSum(userUID: String): Result<Double>
    suspend fun getExpenseSum(userUID: String): Result<Double>
    suspend fun getExpenseByMonths(userUID: String): Result<List<Float>>
}