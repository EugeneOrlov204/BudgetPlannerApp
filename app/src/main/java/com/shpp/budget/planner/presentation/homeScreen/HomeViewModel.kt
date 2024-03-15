package com.shpp.budget.planner.presentation.homeScreen

import androidx.lifecycle.ViewModel
import com.shpp.budget.planner.domain.useCases.auth.GetUserLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserLoginStatusUseCase: GetUserLoginStatusUseCase
) : ViewModel() {
    val isLoggedIn = getUserLoginStatusUseCase()

    val balance: StateFlow<String> = MutableStateFlow("$3,578")
    val month: StateFlow<String> = MutableStateFlow("October")
    val budget: StateFlow<String> = MutableStateFlow("$2,478")
    val income: StateFlow<String> = MutableStateFlow("$1,800.00")
    val expenses: StateFlow<String> = MutableStateFlow("$1,800.00")
}