package com.shpp.budget.planner.presentation.addScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.data.model.Transaction
import com.shpp.budget.planner.domain.useCases.transactions.AddTransactionUseCase
import com.shpp.budget.planner.presentation.utils.ext.toCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val addTransactionUseCase: AddTransactionUseCase) :
    ViewModel() {
    private val _selectedCategory = MutableStateFlow<TransactionCategory?>(null)
    val selectedCategory: StateFlow<TransactionCategory?> = _selectedCategory


    private val _selectedDate = MutableStateFlow(System.currentTimeMillis())
    val selectedDate: StateFlow<Long> = _selectedDate


    fun selectCategory(category: TransactionCategory) {
        viewModelScope.launch {
            _selectedCategory.update {
                if (it == category) null else category
            }
        }
    }

    fun updateDate(tsMillis: Long) {
        viewModelScope.launch {
            _selectedDate.update { tsMillis }
        }
    }

    fun addTransaction(
        isExpense: Boolean,
        amount: String,
        fraction: String,
        onSuccess: () -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        viewModelScope.launch {
            val amountWithFraction = (amount.toInt() + fraction.toInt() / 100f)
            val calendar = Calendar.getInstance().apply {
                timeInMillis = selectedDate.value
            }

            if (isExpense) {
                val category = _selectedCategory.value ?: TransactionCategory.OTHER

                addTransactionUseCase(
                    Transaction.Expense(
                        year = calendar.get(Calendar.YEAR),
                        month = calendar.get(Calendar.MONTH),
                        day = calendar.get(Calendar.DAY_OF_MONTH),
                        amount = amountWithFraction,
                        category = category.toCategory().ordinal
                    )
                )
            } else {
                addTransactionUseCase(
                    Transaction.Income(
                        year = calendar.get(Calendar.YEAR),
                        month = calendar.get(Calendar.MONTH),
                        day = calendar.get(Calendar.DAY_OF_MONTH),
                        amount = amountWithFraction
                    )
                )
            }?.onFailure { onFailure(it.message.orEmpty()) }?.onSuccess { onSuccess() }
                ?: { onFailure("") }
        }
    }
}