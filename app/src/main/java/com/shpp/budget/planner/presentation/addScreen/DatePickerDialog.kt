package com.shpp.budget.planner.presentation.addScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shpp.budget.planner.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    currentDate: Long,
    onDismissRequest: () -> Unit,
    onSubmitDate: (Long) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            val datePickerState =
                rememberDatePickerState(initialSelectedDateMillis = currentDate)
            DatePicker(state = datePickerState)
            Row {
                TextButton(
                    onClick = {
                        onDismissRequest()
                        onSubmitDate(datePickerState.selectedDateMillis ?: 0)
                    }
                ) { Text(stringResource(R.string.ok)) }
                TextButton(
                    onClick = onDismissRequest
                ) { Text(stringResource(R.string.cancel)) }
            }
        }
    }
}

@Preview
@Composable
private fun DatePickerDialogPreview() {
    DatePickerDialog(currentDate = 0L, onDismissRequest = {}, onSubmitDate = {})
}