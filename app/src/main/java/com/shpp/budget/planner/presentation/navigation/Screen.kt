package com.shpp.budget.planner.presentation.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Auth : Screen("auth") {
        data object SignIn : Screen("sign_in")
        data object SignUp : Screen("sign_up")
    }
}