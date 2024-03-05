package com.shpp.budget.planner.presentation.signUpScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.budget.planner.domain.useCases.auth.RegisterUserUseCase
import com.shpp.budget.planner.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow(State())
    val registerState = _registerState.asStateFlow()

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            registerUserUseCase(email, password).collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _registerState.update {
                            it.copy(
                                isLoading = false,
                                state = true,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _registerState.update {
                            it.copy(
                                isLoading = false,
                                state = false,
                                error = resource.message
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _registerState.update {
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