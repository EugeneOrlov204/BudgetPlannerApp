package com.shpp.budget.planner.presentation.signInScreen

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme


@PreviewLightDark
@PreviewScreenSizes
@Composable
fun PreviewSignInScreen() {
    BudgetPlannerAppTheme {
        SignInScreenContent()
    }
}

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onLoggedIn: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var currentToast: Toast? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        onDispose {
            currentToast?.cancel()
        }
    }

    BackHandler {
        (context as ComponentActivity).moveTaskToBack(true)
    }
    SignInScreenContent(
        onLoggedIn = { email, password ->
            viewModel.loginUser(
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
        onSignUpCLick = {
            onSignUpClick()
        }
    )
}

@Composable
fun SignInScreenContent(
    onLoggedIn: (String, String) -> Unit = { _, _ -> },
    onSignUpCLick: () -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.background
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Header(
            Modifier
                .fillMaxSize()
                .weight(2f)
        )
        InputFields(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f)
                .weight(3f)
        ) { email, password ->
            onLoggedIn(email, password)
        }
        SignInWithGoogle(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f)
                .weight(1.5f)
        )
        SignUp(
            Modifier
                .fillMaxSize()
                .weight(1f),
            onSignUpCLick
        )
    }
}


@Composable
fun Header(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun InputFields(modifier: Modifier, onLoggedIn: (String, String) -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email)) },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = password, onValueChange = {
                password = it
            },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .offset(y = dimensionResource(R.dimen.divide_input_fields_space))
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val iconImage = if (passwordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description =
                    if (passwordVisible) stringResource(R.string.hide_password_icon_description)
                    else stringResource(R.string.show_password_icon_description)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
            supportingText = {
                if (isError) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.input_password_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Button(
            onClick = {
                onLoggedIn(email, password)
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_corner_radius)),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.sign_in_button_height)),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            )
        ) {

            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Composable
fun SignInWithGoogle(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.sign_up_with_google_label),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_spacer)))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.extra_small_corner_radius)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_ic),
                contentDescription = stringResource(R.string.google_icon_description),
                Modifier.padding(end = dimensionResource(R.dimen.small_padding))
            )
            Text(
                text = stringResource(R.string.google_sign_up_button_text),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}

@Composable
fun SignUp(modifier: Modifier, onSignUpClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.sign_up_button_label),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_spacer)))
        ClickableText(
            text = buildAnnotatedString {
                append(stringResource(R.string.sign_up_button_text))
            },
            onClick = { onSignUpClick() },
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = dimensionResource(id = R.dimen.sign_up_screen_footer_button_text_size).value.sp
            )
        )
    }
}

