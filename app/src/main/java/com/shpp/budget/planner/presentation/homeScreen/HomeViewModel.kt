package com.shpp.budget.planner.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.useCases.auth.GetUserLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserLoginStatusUseCase: GetUserLoginStatusUseCase
) : ViewModel() {
    val isLoggedIn =
        getUserLoginStatusUseCase().stateIn(viewModelScope, SharingStarted.Lazily, null)
}