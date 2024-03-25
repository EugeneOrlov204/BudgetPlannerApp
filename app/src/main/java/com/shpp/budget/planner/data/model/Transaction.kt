package com.shpp.budget.planner.data.model

sealed class Transaction(
    open val year: Int,
    open val month: Int,
    open val day: Int,
    open val amount: Float
) {
    data class Expense(
        override val year: Int,
        override val month: Int,
        override val day: Int,
        override val amount: Float,
        val category: Int?
    ) : Transaction(year, month, day, amount)

    data class Income(
        override val year: Int,
        override val month: Int,
        override val day: Int,
        override val amount: Float
    ) : Transaction(year, month, day, amount)
}