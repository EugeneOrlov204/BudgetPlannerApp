package com.shpp.budget.planner.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.shpp.budget.planner.data.model.Transaction
import com.shpp.budget.planner.domain.model.TransactionItem.Companion.TRANSACTION_ITEM_DATE_FORMAT
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
) {
    companion object {
        const val TRANSACTION_ITEM_DATE_FORMAT = "d MMM yyyy"
    }
}

fun Transaction.Expense.toTransactionItem(): TransactionItem { // TODO:
    val transactionItem = TransactionCategory.entries.find {
        it.ordinal == category
    } ?: TransactionCategory.OTHER
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateString =
        SimpleDateFormat(TRANSACTION_ITEM_DATE_FORMAT, Locale.getDefault()).format(calendar.time)
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
    val dateString =
        SimpleDateFormat(TRANSACTION_ITEM_DATE_FORMAT, Locale.getDefault()).format(calendar.time)
    return TransactionItem(
        transactionItem.icon,
        transactionItem.type,
        dateString,
        amount,
        transactionItem.color
    )
}
