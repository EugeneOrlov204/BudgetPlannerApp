package com.shpp.budget.planner.presentation.signInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.useCases.auth.LoginUserUseCase
import com.shpp.budget.planner.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(SignInState())
    val loginState = _loginState.asStateFlow()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            loginUserUseCase(email, password).collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _loginState.update {
                            it.copy(
                                isLoading = false,
                                state = true,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _loginState.update {
                            it.copy(
                                isLoading = false,
                                state = false,
                                error = resource.message
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _loginState.update {
                            it.copy(
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

}