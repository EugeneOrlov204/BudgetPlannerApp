package com.shpp.budget.planner.presentation.utils

sealed class Resource<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Success<T> (data: T? = null) : Resource<T>(data)
    class Error<T> (message: String?, data: T? = null) : Resource<T>(data, message)
    class Loading<T> (isLoading: Boolean = true) : Resource<T>(null)
}