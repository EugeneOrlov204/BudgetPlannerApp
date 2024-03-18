package com.shpp.budget.planner.presentation.addScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.components.BottomAppBar
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme
import kotlinx.coroutines.launch

@Composable
fun AddScreen() {
    AddScreenContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddScreenContent() {
    val pagerState = rememberPagerState(pageCount = { AddScreenTab.entries.size })
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.top_app_bar_padding_vertical)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.add_transaction),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicator = { tabPositions ->
                        if (pagerState.currentPage < tabPositions.size) {
                            TabRowDefaults.SecondaryIndicator(
                                Modifier.tabIndicatorOffset(
                                    tabPositions[pagerState.currentPage]
                                ),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                ) {
                    AddScreenTab.entries.forEachIndexed { index, addScreenTab ->
                        Tab(
                            text = {
                                Text(
                                    text = stringResource(id = addScreenTab.title),
                                    color = if (pagerState.currentPage == index) {
                                        MaterialTheme.colorScheme.onPrimaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    },
                                    style = if (pagerState.currentPage == index) {
                                        MaterialTheme.typography.titleMedium
                                    } else {
                                        MaterialTheme.typography.bodyLarge
                                    },
                                )
                            },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            })
                    }
                }
            }
        },
        bottomBar = { BottomAppBar() }
    ) { innerPadding ->
        HorizontalPager(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = dimensionResource(id = R.dimen.add_screen_content_padding_horizontal)),
            state = pagerState
        ) { index ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    when (index) {
                        0 -> {
                            Text(text = "Hello")
                        }

                        1 -> {
                            Text(text = "World")
                        }
                    }
                }
                item { Categories() }
                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.add_screen_confirm_button_padding_vertical))
                            .padding(bottom = dimensionResource(R.dimen.bottom_app_bar_padding_top)),
                        colors = ButtonDefaults.buttonColors()
                            .copy(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            ),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = stringResource(R.string.confirm))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Categories() {
    Column {
        Text(text = stringResource(R.string.from_category))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.add_screen_category_padding_bottom)))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 4,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.add_screen_category_row_padding_vertical),
                Alignment.CenterVertically
            )
        ) {
            TransactionCategory.entries.forEach {
                Icon(
                    modifier = Modifier
                        .border(
                            width = dimensionResource(id = R.dimen.add_screen_category_icon_boarder_width),
                            shape = CircleShape,
                            color = it.color
                        )
                        .padding(dimensionResource(id = R.dimen.add_screen_category_icon_padding)),
                    imageVector = it.icon,
                    contentDescription = it.name,
                    tint = it.color
                )
            }
        }
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Preview
@Composable
private fun AddScreenPreview() {
    BudgetPlannerAppTheme {
        AddScreenContent()
    }
}