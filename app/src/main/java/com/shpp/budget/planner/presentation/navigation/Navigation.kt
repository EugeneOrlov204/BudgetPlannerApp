package com.shpp.budget.planner.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.shpp.budget.planner.presentation.homeScreen.HomeScreen
import com.shpp.budget.planner.presentation.signUpScreen.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onLoggedOut = { navController.navigate(Screen.Auth.route) })
        }
        navigation(route = Screen.Auth.route, startDestination = Screen.Auth.SignUp.route) {
            composable(Screen.Auth.SignUp.route) {
                SignUpScreen(onLoggedIn = {
                    navController.popBackStack(Screen.Auth.route, true)
                    navController.navigate(Screen.Home.route)
                })
            }
        }
    }
}