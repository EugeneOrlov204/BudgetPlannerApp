package com.shpp.budget.planner.presentation.expenseScreen

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.shpp.budget.planner.data.model.Transaction
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class ExpenseItem(
    val type: ImageVector,
    val name: Int,
    val date: String,
    val amount: Float,
    val color: Color
)

fun Transaction.Expense.toExpenseItem(): ExpenseItem {
    val expenseItem = ExpenseCategory.entries.find {
        it.ordinal == category
    } ?: ExpenseCategory.OTHER
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateString = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(calendar.time)
    return ExpenseItem(
        expenseItem.icon,
        expenseItem.type,
        dateString,
        amount,
        expenseItem.color
    )
}
