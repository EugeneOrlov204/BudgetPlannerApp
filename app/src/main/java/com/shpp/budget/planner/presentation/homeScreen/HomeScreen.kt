package com.shpp.budget.planner.presentation.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.navigation.compose.hiltViewModel
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onLoggedOut: () -> Unit) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    when (isLoggedIn) {
        null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        true -> HomeScreenContent()
        false -> LaunchedEffect(isLoggedIn) {
            onLoggedOut()
        }
    }
}

@Composable
fun HomeScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Home")
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Preview
@Composable
fun HomeScreenContentPreview() {
    BudgetPlannerAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreenContent()
        }
    }
}