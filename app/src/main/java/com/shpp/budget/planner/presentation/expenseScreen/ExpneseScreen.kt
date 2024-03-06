package com.shpp.budget.planner.presentation.expenseScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme

@PreviewLightDark
@PreviewScreenSizes
@Composable
fun preview() {
    BudgetPlannerAppTheme {
        ExpenseScreen()
    }

}

@Composable
fun ExpenseScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Header(
            Modifier
                .fillMaxWidth()
        )
        BalanceBoard(
            Modifier
                .fillMaxWidth()
        )
        TransactionsColumn(
            Modifier
                .fillMaxWidth(),
           stringResource(R.string.expense_screen_cash_transactions_title)
        ) {}
        Divider(color = MaterialTheme.colorScheme.onPrimary,
            thickness = dimensionResource(R.dimen.expense_screen_divider_thickness),
            modifier = Modifier.padding(
            horizontal = dimensionResource(R.dimen.expense_screen_horizontal_padding)
        ))
        TransactionsColumn(
            Modifier.fillMaxWidth(),
            stringResource(R.string.expense_screen_ing_transactions_title)
        ) {}
    }

}




@Composable
fun Header(modifier: Modifier) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = stringResource(R.string.expense_screen_header_text),
            fontSize = dimensionResource(R.dimen.expense_screen_header_text_size).value.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.expense_screen_header_text_start_padding),
                bottom = dimensionResource(R.dimen.expense_screen_header_text_bottom_padding),
                top = dimensionResource(R.dimen.expense_screen_header_text_top_padding)

            )
        )

    }
}

@Composable
fun BalanceBoard(modifier: Modifier) {
    val totalBalanceValue by rememberSaveable { mutableStateOf(0f) }
    val ingValue by rememberSaveable { mutableStateOf(0f) }
    val brdValue by rememberSaveable { mutableStateOf(0f) }
    val cashValue by rememberSaveable { mutableStateOf(0f) }
    Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.expense_screen_space_between_header_and_balance_board)))
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    horizontal = dimensionResource(R.dimen.expense_screen_horizontal_padding)
                )
                .shadow(
                    dimensionResource(R.dimen.expense_screen_shadow_size),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius))
                )
                .background(
                    shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius)),
                    color = MaterialTheme.colorScheme.background
                ),

            ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.expense_screen_balance_board_horizontal_padding),
                    vertical = dimensionResource(R.dimen.expense_screen_transaction_icon_vertical_padding)
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.expense_screen_total_balance),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = totalBalanceValue.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.expense_screen_between_balance_text_space)))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.expense_screen_ING_account),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = ingValue.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.expense_screen_between_balance_text_space)))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.expense_screen_brd_account),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = brdValue.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.expense_screen_between_balance_text_space)))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.expense_screen_cash_transactions_title),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = cashValue.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionsColumn(modifier: Modifier,titleText:String,onClick:() -> Unit) {
    Column(
        modifier = modifier.padding(
            vertical = dimensionResource(R.dimen.expense_screen_cash_transactions_column_vertical_padding)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.expense_screen_between_transaction_item_space))
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
        )
        TransactionItem("Car", "12 february 2024", "500")
        TransactionItem("Car", "12 february 2024", "500")
        TransactionItem("Car", "12 february 2024", "500")
        TransactionItem("Car", "12 february 2024", "500")
        ClickableText(
            text = AnnotatedString("See all"),
            onClick = { onClick()},
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.tertiary)
        )
    }
}

@Composable
fun TransactionItem(transactionType: String, transactionDate: String, transactionAmount: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.expense_screen_horizontal_padding))
            .shadow(
                dimensionResource(R.dimen.expense_screen_shadow_size),
                shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius))
            )
            .background(
                shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius)),
                color = MaterialTheme.colorScheme.background
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.Center,
            modifier =Modifier.fillMaxWidth(0.85f)
        ) {
            Image(
                painter = painterResource(R.drawable.car_ic),
                contentDescription = null,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.expense_screen_transaction_icon_vertical_padding))
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.expense_screen_space_between_transaction_image_and_text)))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transactionDate,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = transactionType,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = transactionAmount,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}
