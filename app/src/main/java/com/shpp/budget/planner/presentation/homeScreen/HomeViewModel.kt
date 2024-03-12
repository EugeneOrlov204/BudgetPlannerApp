package com.shpp.budget.planner.presentation.homeScreen

import androidx.lifecycle.ViewModel
import com.shpp.budget.planner.domain.useCases.auth.GetUserLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserLoginStatusUseCase: GetUserLoginStatusUseCase
) : ViewModel() {
    val isLoggedIn = getUserLoginStatusUseCase()
}