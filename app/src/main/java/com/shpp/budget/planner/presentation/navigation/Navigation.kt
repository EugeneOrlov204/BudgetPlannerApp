package com.shpp.budget.planner.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.shpp.budget.planner.presentation.homeScreen.HomeScreen
import com.shpp.budget.planner.presentation.signInScreen.SignInScreen
import com.shpp.budget.planner.presentation.signUpScreen.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onLoggedOut = { navController.navigate(Screen.Auth.route) })
        }
        navigation(route = Screen.Auth.route, startDestination = Screen.Auth.SignIn.route) {
            composable(Screen.Auth.SignUp.route) {
                SignUpScreen(
                    onLoggedIn = {
                        navController.popBackStack(Screen.Auth.route, true)
                    },
                    onSignInButtonClick = {
                        navigate(navController, Screen.Auth.SignIn.route) {
                            navController.navigateUp()
                        }
                    }
                )
            }
            composable(Screen.Auth.SignIn.route) {
                SignInScreen(
                    onLoggedIn = {
                        navController.navigateUp()
                    },
                    onSignUpClick = {
                        navigate(navController, Screen.Auth.SignUp.route) {
                            navController.navigate(Screen.Auth.SignUp.route)
                        }
                    }
                )
            }

        }
    }

}

fun navigate(navController: NavController, desiredScreen: String, action: () -> Unit) {
    val currentDestination = navController.currentBackStackEntry?.destination?.route
    if (currentDestination != desiredScreen) {
        action()
    }
}