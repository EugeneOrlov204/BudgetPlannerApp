package com.shpp.budget.planner.presentation.signInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.useCases.auth.LoginUserUseCase
import com.shpp.budget.planner.presentation.utils.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(AuthState(false))
    val loginState = _loginState.asStateFlow()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val result = loginUserUseCase(email, password)
            if (result.isSuccess) {
                _loginState.update {
                    AuthState(true, null)
                }
            } else {
                _loginState.update {
                    AuthState(false, result.exceptionOrNull()?.message)
                }
            }
        }
    }

}