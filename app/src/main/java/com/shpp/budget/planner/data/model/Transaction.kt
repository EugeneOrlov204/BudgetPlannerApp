package com.shpp.budget.planner.data.model

sealed class Transaction(
    open val year: Int,
    open val month: Int,
    open val day: Int,
    open val amount: Float
) {
    data class Expense(
        override val year: Int = 0,
        override val month: Int = 0,
        override val day: Int = 0,
        override val amount: Float = 0.0f,
        val category: Int? = 8
    ) : Transaction(year, month, day, amount)

    data class Income(
        override val year: Int = 0,
        override val month: Int = 0,
        override val day: Int = 0,
        override val amount: Float = 0.0f
    ) : Transaction(year, month, day, amount)
}