package com.shpp.budget.planner.presentation.signInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R


@Composable
fun SignInScreen(onLoggedIn: () -> Boolean, onSignUp: () -> Unit) {
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
            )
            .padding(horizontal = dimensionResource(id = R.dimen.sign_in_screen_main_column_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Header(
            Modifier
                .fillMaxSize()
                .weight(2f)
        )
        InputFields(
            Modifier
                .fillMaxSize()
                .weight(3f)
        )
        SignInWithGoogle(
            Modifier
                .fillMaxSize()
                .weight(1.5f)
        )
        SignUp(
            Modifier
                .fillMaxSize()
                .weight(1f),
            onSignUp
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
fun InputFields(modifier: Modifier) {
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
        Spacer(
            modifier = Modifier.height(dimensionResource(R.dimen.sign_in_screen_padding_between_text_field_and_button))
        )
        Button(
            onClick = { /*TODO*/ },
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
                color = Color.White
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
                containerColor = Color.White
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
fun SignUp(modifier: Modifier, onSignUp: () -> Unit) {
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
            onClick = { onSignUp() },
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = dimensionResource(id = R.dimen.sign_up_screen_footer_button_text_size).value.sp
            )
        )
    }
}

