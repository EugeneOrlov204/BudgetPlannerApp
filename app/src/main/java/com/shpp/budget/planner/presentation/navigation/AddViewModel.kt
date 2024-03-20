package com.shpp.budget.planner.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.presentation.addScreen.TransactionCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DateFormat
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {
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

    fun addTransaction(isExpense: Boolean, amount: String, fraction: String) {
        viewModelScope.launch {

        }
    }
}