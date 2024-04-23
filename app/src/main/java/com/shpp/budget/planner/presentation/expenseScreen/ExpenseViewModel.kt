package com.shpp.budget.planner.presentation.expenseScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.model.TransactionItem
import com.shpp.budget.planner.domain.model.toTransactionItem
import com.shpp.budget.planner.domain.useCases.transactions.GetBudgetUseCase
import com.shpp.budget.planner.domain.useCases.transactions.GetExpensesByMonthsUseCase
import com.shpp.budget.planner.domain.useCases.transactions.GetExpensesUseCase
import com.shpp.budget.planner.domain.useCases.transactions.GetIncomesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
    private val getBudgetUseCase: GetBudgetUseCase,
    private val getExpensesByMonthsUseCase: GetExpensesByMonthsUseCase,
    private val getIncomesUseCase: GetIncomesUseCase
) : ViewModel() {

    val expensesByMonths: MutableStateFlow<List<Float>> = MutableStateFlow(mutableListOf())
    val budget: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val expenses: MutableStateFlow<List<TransactionItem>> = MutableStateFlow(mutableListOf())
    val incomes: MutableStateFlow<List<TransactionItem>> = MutableStateFlow(mutableListOf())
    val transactions: MutableStateFlow<List<TransactionItem>> = MutableStateFlow(mutableListOf())

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
                            transaction.toTransactionItem()
                        }
                    }
                }

            getIncomesUseCase()
                ?.onSuccess { list ->
                    incomes.update {
                        list.map { transaction ->
                            transaction.toTransactionItem()
                        }
                    }
                }

            val groupedExpenses = expenses.value
                .groupBy { it.type }
                .mapValues { (_, items) ->
                    val newDate = items.maxByOrNull { it.date }?.date ?: ""
                    val sumAmount = items.sumOf { it.amount.toDouble() }.toFloat()
                    TransactionItem(items.first().type, items.first().name, newDate, sumAmount, items.first().color)
                }
                .values
                .sortedByDescending { it.date }
                .toList()

            val groupedIncomes = incomes.value
                .groupBy { it.type }
                .mapValues { (_, items) ->
                    val newDate = items.maxByOrNull { it.date }?.date ?: ""
                    val sumAmount = items.sumOf { it.amount.toDouble() }.toFloat()
                    TransactionItem(items.first().type, items.first().name, newDate, sumAmount, items.first().color)
                }
                .values
                .sortedByDescending { it.date }
                .toList()

            transactions.update {
                groupedIncomes + groupedExpenses
            }

        }
    }

}