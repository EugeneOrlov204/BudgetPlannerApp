package com.shpp.budget.planner.presentation.addScreen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.navigation.compose.hiltViewModel
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.components.BottomAppBar
import com.shpp.budget.planner.presentation.components.BottomBarScreen
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme
import kotlinx.coroutines.launch
import java.text.DateFormat

@Composable
fun AddScreen(
    viewModel: AddViewModel = hiltViewModel(),
    onScreenClick: (screen: BottomBarScreen) -> Unit
) {
    val appContext = LocalContext.current.applicationContext
    val unknownErrorStr = stringResource(id = R.string.unknown_error)
    val transactionAddedStr = stringResource(id = R.string.transaction_added)
    val emptyTransactionErrorStr = stringResource(id = R.string.empty_transaction_error)
    AddScreenContent(
        selectedDate = viewModel.selectedDate.collectAsState().value,
        selectedDateFormatted = DateFormat.getDateInstance().format(viewModel.selectedDate.value),
        onSelectDate = viewModel::updateDate,
        selectedCategory = viewModel.selectedCategory.collectAsState().value,
        onCategoryClick = viewModel::selectCategory,
        onScreenClick = onScreenClick,
        onConfirmClick = { isExpense: Boolean, amount: String, fraction: String ->

            if (amount.toInt() == 0 && fraction.toInt() == 0) {
                Toast.makeText(appContext, emptyTransactionErrorStr, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addTransaction(
                    isExpense = isExpense,
                    amount = amount,
                    fraction = fraction,
                    onSuccess = {

                        Toast.makeText(appContext, transactionAddedStr, Toast.LENGTH_SHORT).show()
                    },
                    onFailure = {
                        Toast.makeText(
                            appContext,
                            it.ifEmpty { unknownErrorStr },
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddScreenContent(
    selectedDate: Long = 0,
    selectedDateFormatted: String,
    onSelectDate: (Long) -> Unit = {},
    selectedCategory: TransactionCategory?,
    onCategoryClick: (TransactionCategory) -> Unit = {},
    onScreenClick: (screen: BottomBarScreen) -> Unit = {},
    onConfirmClick: (isExpense: Boolean, amount: String, fraction: String) -> Unit = { _, _, _ -> }
) {
    val pagerState = rememberPagerState(pageCount = { AddScreenTab.entries.size })
    val coroutineScope = rememberCoroutineScope()
    var shouldShowPicker by remember { mutableStateOf(false) }
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
        bottomBar = { BottomAppBar(onScreenClick = onScreenClick) }
    ) { innerPadding ->
        HorizontalPager(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.surface),
            state = pagerState
        ) { index ->
            when (index) {
                0 -> {
                    val amount = rememberSaveable { mutableStateOf("0") }
                    val fraction = rememberSaveable { mutableStateOf("00") }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = dimensionResource(id = R.dimen.add_screen_content_padding_horizontal))
                    ) {
                        item {
                            InputFields(amount = amount, fraction = fraction)
                        }
                        item {
                            Date(
                                selectedDateFormatted = selectedDateFormatted,
                                onCalendarClick = { shouldShowPicker = true }
                            )
                        }
                        item {
                            ConfirmButton(onClick = {
                                onConfirmClick(false, amount.value, fraction.value)
                            })
                        }
                    }
                }

                1 -> {
                    val amount = rememberSaveable { mutableStateOf("0") }
                    val fraction = rememberSaveable { mutableStateOf("00") }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = dimensionResource(id = R.dimen.add_screen_content_padding_horizontal))
                    ) {
                        item {
                            InputFields(
                                isIncome = false,
                                amount = amount,
                                fraction = fraction
                            )
                        }
                        item {
                            Date(
                                selectedDateFormatted = selectedDateFormatted,
                                onCalendarClick = { shouldShowPicker = true }
                            )
                        }
                        item {
                            Categories(
                                selectedCategory = selectedCategory,
                                onCategoryClick = onCategoryClick
                            )
                        }
                        item {
                            ConfirmButton(onClick = {
                                onConfirmClick(true, amount.value, fraction.value)
                            })
                        }
                    }
                }
            }
        }
        if (shouldShowPicker) {
            DatePickerDialog(
                currentDate = selectedDate,
                onSubmitDate = onSelectDate,
                onDismissRequest = { shouldShowPicker = false }
            )
        }
    }
}

@Composable
private fun InputFields(
    isIncome: Boolean = true, amount: MutableState<String>, fraction: MutableState<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.add_screen_input_padding_vertical)),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isIncome) "+" else "-",
            style = MaterialTheme.typography.displayMedium
        )
        BasicTextField(
            modifier = Modifier
                .weight(1f, false)
                .width(IntrinsicSize.Min),
            value = amount.value,
            onValueChange = {
                val srtBuilder = StringBuilder()
                run breaking@{
                    it.forEach { char ->
                        if (char.isDigit()) {
                            srtBuilder.append(char)
                        }
                    }
                }
                amount.value = if (srtBuilder.isEmpty()) "0" else srtBuilder.toString()
            },
            textStyle = MaterialTheme.typography.displayMedium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Text(text = ",", style = MaterialTheme.typography.displayMedium)
        BasicTextField(
            modifier = Modifier.width(IntrinsicSize.Min),
            value = fraction.value,
            onValueChange = {
                val srtBuilder = StringBuilder()
                run breaking@{
                    it.forEach { char ->
                        if (char.isDigit()) {
                            srtBuilder.append(char)
                        }
                        if (srtBuilder.length == 2) return@breaking
                    }
                }
                for (i in 1..2 - srtBuilder.length) {
                    srtBuilder.append("0")
                }
                fraction.value = srtBuilder.toString()
            },
            textStyle = MaterialTheme.typography.displayMedium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Text(text = "$", style = MaterialTheme.typography.displayMedium)
    }
}

@Composable
fun Date(selectedDateFormatted: String, onCalendarClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "${stringResource(R.string.date)}: $selectedDateFormatted",
            style = MaterialTheme.typography.titleMedium
        )
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onCalendarClick() }
                .padding(dimensionResource(id = R.dimen.add_screen_calendar_icon_padding)),
            imageVector = Icons.Filled.CalendarMonth,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Categories(
    selectedCategory: TransactionCategory?,
    onCategoryClick: (TransactionCategory) -> Unit
) {
    Column {
        Text(text = stringResource(R.string.from_category))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.add_screen_category_padding_bottom)))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.add_screen_category_row_padding_vertical),
                Alignment.CenterVertically
            )
        ) {
            TransactionCategory.entries.forEach {
                Icon(
                    modifier = Modifier
                        .background(
                            color = if (selectedCategory == it) it.color.copy(alpha = 0.5f) else Color.Transparent,
                            shape = CircleShape
                        )

                        .border(
                            width = dimensionResource(id = R.dimen.add_screen_category_icon_boarder_width),
                            shape = CircleShape,
                            color = it.color
                        )
                        .clip(CircleShape)
                        .clickable { onCategoryClick(it) }
                        .padding(dimensionResource(id = R.dimen.add_screen_category_icon_padding)),
                    imageVector = it.icon,
                    contentDescription = it.name,
                    tint = it.color
                )
            }
        }
    }
}

@Composable
private fun ConfirmButton(onClick: () -> Unit) {
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
        onClick = onClick
    ) {
        Text(text = stringResource(R.string.confirm))
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Preview
@Composable
private fun AddScreenPreview() {
    BudgetPlannerAppTheme {
        AddScreenContent(
            selectedDateFormatted = "Mar 19,2024",
            selectedCategory = TransactionCategory.BEAUTY
        )
    }
}