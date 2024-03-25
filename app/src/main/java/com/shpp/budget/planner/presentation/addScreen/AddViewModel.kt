package com.shpp.budget.planner.presentation.addScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.data.model.Transaction
import com.shpp.budget.planner.domain.useCases.transactions.AddTransactionUseCase
import com.shpp.budget.planner.presentation.utils.ext.toCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val addTransactionUseCase: AddTransactionUseCase) :
    ViewModel() {
    private val _selectedCategory = MutableStateFlow<TransactionCategory?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()
    private val localDateFormat = DateFormat.getDateInstance()
    private val _selectedDate = MutableStateFlow(System.currentTimeMillis())
    val selectedDate = _selectedDate.asStateFlow()
    val selectedDateString = _selectedDate.map {
        localDateFormat.format(it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

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
            val c = Calendar.getInstance().apply {
                timeInMillis = selectedDate.value
            }
            if (isExpense) {
                val category = _selectedCategory.value?.toCategory()
                addTransactionUseCase(
                    Transaction.Expense(
                        year = c.get(Calendar.YEAR),
                        month = c.get(Calendar.MONTH),
                        day = c.get(Calendar.DAY_OF_MONTH),
                        amount = amountWithFraction,
                        category = category?.code
                    )
                )
            } else {
                addTransactionUseCase(
                    Transaction.Income(
                        year = c.get(Calendar.YEAR),
                        month = c.get(Calendar.MONTH),
                        day = c.get(Calendar.DAY_OF_MONTH),
                        amount = amountWithFraction
                    )
                )
            }?.onFailure { onFailure(it.message.orEmpty()) }?.onSuccess { onSuccess() }
                ?: { onFailure("") }
        }
    }
}