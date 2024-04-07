package com.shpp.budget.planner.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.shpp.budget.planner.presentation.components.BottomBarScreen
import com.shpp.budget.planner.presentation.expenseScreen.ExpenseScreen
import com.shpp.budget.planner.presentation.homeScreen.HomeScreen
import com.shpp.budget.planner.presentation.signInScreen.SignInScreen
import com.shpp.budget.planner.presentation.signUpScreen.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onLoggedOut = { navController.navigate(Screen.Auth.route) },
                onScreenClick = {
                    when (it) {
                        BottomBarScreen.WALLET -> {
                            navController.performIfCurrentDestinationDoesntMatch(Screen.Expense.route) {
                                navController.navigate(Screen.Expense.route)
                            }
                        }

                        else -> {}
                    }
                }
            )
        }
        composable(Screen.Expense.route) {
            ExpenseScreen(onScreenClick = {
                when (it) {
                    BottomBarScreen.HOME ->
                        navController.performIfCurrentDestinationDoesntMatch(Screen.Home.route) {
                            navController.navigate(Screen.Home.route)
                        }

                    else -> {}
                }
            })
        }

        navigation(route = Screen.Auth.route, startDestination = Screen.Auth.SignIn.route) {
            composable(Screen.Auth.SignUp.route) {
                SignUpScreen(
                    onLoggedIn = {
                        navController.popBackStack(Screen.Auth.route, true)
                    },
                    onSignInButtonClick = {
                        navController.performIfCurrentDestinationDoesntMatch(Screen.Auth.SignIn.route) {
                            navigateUp()
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
                        navController.performIfCurrentDestinationDoesntMatch(Screen.Auth.SignUp.route) {
                            navigate(Screen.Auth.SignUp.route)
                        }
                    }
                )
            }

        }
    }

}

fun NavController.performIfCurrentDestinationDoesntMatch(
    secondDestination: String,
    action: NavController.() -> Unit
) {
    if (currentDestination?.route != secondDestination) {
        action()
    }
}