package com.shpp.budget.planner.presentation.expenseScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R
import com.shpp.budget.planner.domain.model.Transaction
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme

@PreviewLightDark
@PreviewScreenSizes
@Composable
fun Preview() {
    BudgetPlannerAppTheme {
        ExpenseScreen()
    }
}

@Composable
fun ExpenseScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Header(Modifier.fillMaxWidth())
        BalanceBoard(Modifier.fillMaxWidth())
        TransactionsColumn(
            Modifier.fillMaxWidth(),
            //Todo get list from viewModel
            List(4) {
                Transaction(
                    typeName = "car",
                    date = "11 March 2024",
                    transactionAmount = 500f
                )
            },
            stringResource(R.string.expense_screen_cash_transactions_title)
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.expense_screen_horizontal_padding)
            ),
            thickness = dimensionResource(R.dimen.expense_screen_divider_thickness),

            color = MaterialTheme.colorScheme.onPrimary
        )
        TransactionsColumn(
            Modifier.fillMaxWidth(),
            List(4) {
                Transaction(
                    typeName = "car",
                    date = "11 March 2024",
                    transactionAmount = 500f
                )
            },
            stringResource(R.string.expense_screen_ing_transactions_title)
        )
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
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.expense_screen_space_between_header_and_balance_board)))

    Row(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(R.dimen.expense_screen_horizontal_padding),
            )
            .shadow(
                dimensionResource(R.dimen.expense_screen_shadow_size),
                shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius))
            )
            .background(
                shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius)),
                color = MaterialTheme.colorScheme.background
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.expense_screen_balance_board_horizontal_padding),
                top = dimensionResource(R.dimen.expense_screen_balance_board_vertical_padding),
                bottom = dimensionResource(R.dimen.expense_screen_balance_board_vertical_padding)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.expense_screen_between_balance_text_space))
        ) {
            Text(
                text = stringResource(R.string.expense_screen_total_balance),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = stringResource(R.string.expense_screen_ING_account),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = stringResource(R.string.expense_screen_brd_account),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = stringResource(R.string.expense_screen_cash_transactions_title),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Column(
            modifier = Modifier.padding(
                end = dimensionResource(R.dimen.expense_screen_balance_board_horizontal_padding),
                top = dimensionResource(R.dimen.expense_screen_balance_board_vertical_padding),
                bottom = dimensionResource(R.dimen.expense_screen_balance_board_vertical_padding)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.expense_screen_between_balance_text_space))
        ) {
            Text(
                text = totalBalanceValue.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = ingValue.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = brdValue.toString(),
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

@Composable
fun TransactionsColumn(
    modifier: Modifier,
    transactionItems: List<Transaction>,
    titleText: String,
    onClick: () -> Unit = {}
) {
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
        for (t in transactionItems) {
            TransactionItem(t.typeName, t.date, t.transactionAmount)
        }
        Button(
            onClick = onClick, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = stringResource(R.string.expense_screen_to_transaction_screen_button_text),
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.inverseOnSurface)
            )
        }
    }
}

@Composable
fun TransactionItem(transactionType: String, transactionDate: String, transactionAmount: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
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
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
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
            text = transactionAmount.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(0.1f))
    }
}
