package com.shpp.budget.planner.presentation.signUpScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onLoggedIn: () -> Unit = {},
    onSignInButtonClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var currentToast: Toast? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        onDispose {
            currentToast?.cancel()
        }
    }

    SignUpScreenContent(
        onSignUpButtonClick = { email, password ->
            viewModel.registerUser(
                email = email,
                password = password,
                onSuccess = onLoggedIn,
                onFailure = {
                    currentToast?.cancel()
                    currentToast = Toast.makeText(context, it, Toast.LENGTH_SHORT)
                    currentToast?.show()
                }
            )
        },
        onSignIn = {
            onSignInButtonClick()
        }
    )
}

@Composable
fun SignUpScreenContent(
    onSignUpButtonClick: (String, String) -> Unit = { _, _ -> },
    onSignIn: () -> Unit = {}
) {
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
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
        )
        TextFieldsWithButton(
            modifier = Modifier
                .weight(4.5f)
                .fillMaxWidth(0.9f),
            onSignUpButtonClick = { email, password ->
                onSignUpButtonClick(email, password)
            }
        )
        Footer(
            modifier = Modifier
                .weight(1.1f)
                .fillMaxSize()
        ) {
            onSignIn()
        }
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
fun TextFieldsWithButton(
    modifier: Modifier = Modifier,
    onSignUpButtonClick: (String, String) -> Unit
) {
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.background
            )
        )

        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.divide_input_fields_space)))

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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation =
            if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
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
            },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.background
            )
        )

        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.divide_input_fields_space)))

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.sign_up_screen_button_height)),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.sign_up_screen_button_corner_radius)),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            ),
            elevation = ButtonDefaults.buttonElevation(dimensionResource(id = R.dimen.sign_up_screen_button_elevation)),
            onClick = {
                onSignUpButtonClick(emailText, passwordText)
            }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_create_account_button),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.background
            )
        }
    }

}

@Composable
fun Footer(modifier: Modifier = Modifier, onSignIn: () -> Unit) {
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
        ClickableText(
            onClick = { onSignIn() },
            text = AnnotatedString(stringResource(id = R.string.sign_up_screen_bottom_sign_in_button)),
            style = TextStyle(
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                color = MaterialTheme.colorScheme.primary,
                fontSize = dimensionResource(id = R.dimen.sign_up_screen_footer_button_text_size).value.sp
            )
        )
    }
}


@PreviewLightDark
@PreviewScreenSizes
@Composable
fun SignUpScreenPreviewLight() {
    BudgetPlannerAppTheme {
        SignUpScreenContent()
    }
}