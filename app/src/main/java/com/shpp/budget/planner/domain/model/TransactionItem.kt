package com.shpp.budget.planner.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.shpp.budget.planner.data.model.Transaction
import com.shpp.budget.planner.presentation.expenseScreen.TransactionCategory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class TransactionItem(
    val type: ImageVector,
    val name: Int,
    val date: String,
    val amount: Float,
    val color: Color
)

fun Transaction.Expense.toTransactionItem(): TransactionItem { // TODO:
    val transactionItem = TransactionCategory.entries.find {
        it.ordinal == category
    } ?: TransactionCategory.OTHER
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateString = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(calendar.time)
    return TransactionItem(
        transactionItem.icon,
        transactionItem.type,
        dateString,
        amount,
        transactionItem.color
    )
}

fun Transaction.Income.toTransactionItem(): TransactionItem {
    val transactionItem = TransactionCategory.INCOME
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateString = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(calendar.time)
    return TransactionItem(
        transactionItem.icon,
        transactionItem.type,
        dateString,
        amount,
        transactionItem.color
    )
}
