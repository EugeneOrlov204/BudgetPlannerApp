package com.shpp.budget.planner.presentation.resetPasswordScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.useCases.auth.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
): ViewModel() {

    fun resetPassword(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            resetPasswordUseCase(email)
                .onSuccess {
                    onSuccess()
                }
                .onFailure { exception ->
                    onFailure(exception.localizedMessage.orEmpty())
                }
        }
    }

}