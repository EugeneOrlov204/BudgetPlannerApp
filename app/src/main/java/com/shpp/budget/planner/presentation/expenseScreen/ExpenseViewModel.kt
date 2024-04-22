package com.shpp.budget.planner.presentation.expenseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.model.ExpenseItem
import com.shpp.budget.planner.domain.model.toExpenseItem
import com.shpp.budget.planner.domain.useCases.transactions.GetBudgetUseCase
import com.shpp.budget.planner.domain.useCases.transactions.GetExpensesByMonthsUseCase
import com.shpp.budget.planner.domain.useCases.transactions.GetExpensesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val getExpensesByMonthsUseCase: GetExpensesByMonthsUseCase
) : ViewModel() {

    val expensesByMonths: MutableStateFlow<List<Float>> = MutableStateFlow(mutableListOf())
    val budget: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val expenses: MutableStateFlow<List<ExpenseItem>> = MutableStateFlow(mutableListOf())

    init {
        viewModelScope.launch {

            expensesByMonths.update {
                getExpensesByMonthsUseCase()?.getOrNull() ?: emptyList()
            }

            budget.update {
                "%.2f".format(getBudgetUseCase() ?: 0.0).replace(",", ".").toDouble()
            }

            getExpensesUseCase()
                ?.onSuccess { list ->
                    expenses.update {
                        list.map { transaction ->
                            transaction.toExpenseItem()
                        }
                    }
                }

        }
    }

}