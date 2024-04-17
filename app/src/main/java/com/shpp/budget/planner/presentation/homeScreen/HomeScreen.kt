package com.shpp.budget.planner.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.navigation.compose.hiltViewModel
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.components.BottomAppBar
import com.shpp.budget.planner.presentation.components.BottomBarScreen
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(), onLoggedOut: () -> Unit,
    onPlusClick: () -> Unit,
    onScreenClick: (screen: BottomBarScreen) -> Unit
) {
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

        true -> HomeScreenContent(
            balance = viewModel.balance.collectAsState().value,
            month = viewModel.month.collectAsState().value,
            budget = viewModel.budget.collectAsState().value,
            income = viewModel.income.collectAsState().value,
            expenses = viewModel.expenses.collectAsState().value,
            onPlusClick = onPlusClick,
            onScreenClick = onScreenClick
        )

        false -> LaunchedEffect(isLoggedIn) {
            onLoggedOut()
        }
    }
}

@Composable
fun HomeScreenContent(
    balance: String,
    month: String,
    budget: String,
    income: String,
    expenses: String,
    onPlusClick: () -> Unit = {},
    onScreenClick: (screen: BottomBarScreen) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(vertical = dimensionResource(R.dimen.top_app_bar_padding_vertical)),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.dashboard),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                currentScreen = BottomBarScreen.HOME,
                onPlusClick = onPlusClick,
                onScreenClick = onScreenClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = dimensionResource(id = R.dimen.home_screen_content_padding_horizontal))
        ) {
            item { Balance(balance) }
            item { Budget(month, budget) }
            item { SavingGoal() }
            item { Cash(income, expenses) }
        }
    }
}

@Composable
fun Balance(balance: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.home_screen_available_balance_padding_vertical))
            .elementCommonModifier()
    ) {
        Text(
            text = stringResource(R.string.available_balance),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.home_screen_balance_text_padding_top)))
        Text(text = balance, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.home_screen_balance_text_padding_bottom)))
        Text(
            text = stringResource(R.string.see_details),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun Budget(month: String, budget: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .elementCommonModifier(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.budget_for, month),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.home_screen_small_text_padding)))
            Text(
                text = stringResource(R.string.cash_available),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(text = budget, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun SavingGoal() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.home_screen_saving_goals_element_padding_vertical))
            .elementCommonModifier(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.create_a_saving_goal),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.home_screen_small_text_padding)))
            Text(
                text = stringResource(R.string.placeholder_text),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.home_screen_icon_placeholder_size))
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
        )
    }
}

@Composable
fun Cash(income: String, expenses: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.bottom_app_bar_padding_top))
    ) {
        Text(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.home_screen_cash_text_padding_vertical)),
            text = stringResource(R.string.cash),
            style = MaterialTheme.typography.bodyLarge
        )
        Row(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.home_screen_cash_padding_bottom))) {
            CashCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = dimensionResource(id = R.dimen.home_screen_cash_elements_spacing))
                    .elementCommonModifier(),
                cash = income,
                description = stringResource(R.string.income)
            )
            CashCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.home_screen_cash_elements_spacing))
                    .elementCommonModifier(),
                cash = expenses,
                description = stringResource(R.string.expenses)
            )
        }
    }
}

@Composable
private fun CashCard(modifier: Modifier = Modifier, cash: String, description: String) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.home_screen_icon_placeholder_size))
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.home_screen_cash_element_text_padding_top)))
        Text(text = cash, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.home_screen_cash_element_text_padding_bottom)))
        Text(
            text = description,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun Modifier.elementCommonModifier() = this
    .shadow(
        dimensionResource(id = R.dimen.home_screen_elements_shadow_elevation),
        RoundedCornerShape(dimensionResource(id = R.dimen.home_screen_elements_corner_radius))
    )
    .background(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.home_screen_elements_corner_radius))
    )
    .padding(dimensionResource(id = R.dimen.home_screen_elements_content_padding))

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
            HomeScreenContent(
                balance = "$3,578",
                month = "October",
                budget = "$2,478",
                income = "$1,800.00",
                expenses = "$1,800.00",
                onScreenClick = {}
            )
        }
    }
}