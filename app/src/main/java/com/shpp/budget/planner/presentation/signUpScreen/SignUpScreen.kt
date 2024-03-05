package com.shpp.budget.planner.presentation.signUpScreen

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme
import com.shpp.budget.planner.presentation.utils.PASSWORD_MASK

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel(), onLoggedIn: () -> Unit) {
    val context = LocalContext.current
    val registerState = viewModel.registerState.collectAsState()

    if (registerState.value.state) {
        onLoggedIn()
    }
    if (!registerState.value.error.isNullOrBlank()) {
        Toast.makeText(
            context,
            registerState.value.error,
            Toast.LENGTH_SHORT
        ).show()
    }

    BackHandler {
        (context as ComponentActivity).moveTaskToBack(true)
    }
    Box {
        SignUpScreenContent { email, password ->
            viewModel.registerUser(email, password)
        }
        if (registerState.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.BottomCenter))
        }

    }
}

@Composable
fun SignUpScreenContent(onSignUpClick: (String, String) -> Unit) {
    Column(
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
            .padding(horizontal = dimensionResource(id = R.dimen.sign_up_screen_main_column_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
        )
        TextFieldsWithButton(
            modifier = Modifier
                .weight(2.5f)
                .fillMaxWidth(0.9f),
            //.fillMaxSize(),
            onSignUpClick = { email, password ->
                onSignUpClick(email, password)
            }
        )
        Footer(
            modifier = Modifier
                .weight(1.5f)
                .fillMaxSize()
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_screen_header),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun TextFieldsWithButton(modifier: Modifier = Modifier, onSignUpClick: (String, String) -> Unit) {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.sign_up_screen_text_field_height)),
            value = emailText,
            onValueChange = {
                emailText = it
            },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.sign_up_screen_email_text_field)) },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.sign_up_screen_padding_between_text_fields)))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.sign_up_screen_text_field_height)),
            value = passwordText,
            onValueChange = {
                passwordText = it
            },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.sign_up_screen_password_text_field)) },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation =
            if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation(PASSWORD_MASK)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.sign_up_screen_padding_between_text_field_and_button)))

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.sign_up_screen_button_height)),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.sign_up_screen_button_corner_radius)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.sign_up_screen_button_elevation)),
            onClick = {
                onSignUpClick(emailText, passwordText)
            }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_create_account_button),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_screen_already_a_member),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.sign_up_screen_default_padding)))
        Text(
            modifier = Modifier
                .clickable {
                    // TODO:  
                },
            text = stringResource(id = R.string.sign_up_screen_bottom_sign_in_button),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            color = MaterialTheme.colorScheme.primary,
            fontSize = dimensionResource(id = R.dimen.sign_up_screen_footer_button_text_size).value.sp
        )
    }
}


@PreviewLightDark
@PreviewScreenSizes
@Composable
fun SignUpScreenPreviewLight() {
    BudgetPlannerAppTheme {
        SignUpScreenContent { _, _ -> }
    }
}