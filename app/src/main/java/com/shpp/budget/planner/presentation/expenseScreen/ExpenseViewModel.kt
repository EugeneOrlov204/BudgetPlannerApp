package com.shpp.budget.planner.presentation.expenseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.useCases.transactions.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    val expenses: MutableStateFlow<List<ExpenseItem>> = MutableStateFlow(mutableListOf())

    init {
        viewModelScope.launch {
            getTransactionsUseCase()
                ?.onSuccess { list ->
                    expenses.update {
                        list.map { transaction ->
                            transaction.toExpenseItem()
                        }
                    }
                }
                ?.onFailure {

                }
        }
    }

}