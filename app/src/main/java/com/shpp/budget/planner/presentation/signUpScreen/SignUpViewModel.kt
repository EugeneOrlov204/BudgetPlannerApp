package com.shpp.budget.planner.presentation.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.useCases.auth.RegisterUserUseCase
import com.shpp.budget.planner.presentation.utils.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow(AuthState(false))
    val registerState = _registerState.asStateFlow()

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            val result = registerUserUseCase(email, password)
            if (result.isSuccess) {
                _registerState.update {
                    AuthState(true, null)
                }
            } else {
                _registerState.update {
                    AuthState(false, result.exceptionOrNull()?.message)
                }
            }
        }
    }
}