package com.shpp.budget.planner.presentation.resetPasswordScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme

@Composable
fun ResetPasswordScreen(
    email: String = "",
    viewModel: ResetPasswordViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var currentToast: Toast? by remember { mutableStateOf(null) }

    ResetPasswordScreenContent(
        email = email,
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background
                    )
                )
            ),
        onResetPasswordClick = {
            viewModel.resetPassword(
                email = it,
                onSuccess = {
                    currentToast?.cancel()
                    currentToast = Toast.makeText(
                        context,
                        R.string.sign_up_reset_password_success,
                        Toast.LENGTH_SHORT
                    )
                    currentToast?.show()
                },
                onFailure = {
                    currentToast?.cancel()
                    currentToast = Toast.makeText(context, it, Toast.LENGTH_SHORT)
                    currentToast?.show()
                }
            )
        }
    )
}

@Composable
fun ResetPasswordScreenContent(
    modifier: Modifier = Modifier,
    email: String = "",
    onResetPasswordClick: (String) -> Unit = {}
) {
    var emailValue by remember { mutableStateOf(email) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(id = R.string.reset_password_header),
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            value = emailValue,
            onValueChange = {
                emailValue = it
            },
            label = { Text(text = stringResource(R.string.email)) },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.background
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth(0.85f)
        )
        Button(
            onClick = {
                onResetPasswordClick(emailValue)
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_corner_radius)),
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(dimensionResource(R.dimen.sign_in_button_height)),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = stringResource(id = R.string.reset_password_button),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.background
                )
            )
        }

    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
fun PreviewSignInScreen() {
    BudgetPlannerAppTheme {
        ResetPasswordScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )
    }
}