package com.shpp.budget.planner.presentation.signUpScreen

import android.content.res.Configuration
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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.utils.Constants.PASSWORD_MASK

@Composable
fun SignUpScreen() {
    Box(
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
            .padding(horizontal = dimensionResource(id = R.dimen.sign_up_screen_main_column_padding))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
            )
            TextFieldsWithButton(
                modifier = Modifier
                    .weight(2.5f)
                    .fillMaxSize()
            )
            Footer(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxSize()
            )
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
fun TextFieldsWithButton(modifier: Modifier = Modifier) {
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
                        imageVector =
                        if (isPasswordVisible) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
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
            onClick = { /*TODO*/ }
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
            fontWeight = FontWeight(600),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp
        )
    }
}


@Preview(
    name = "Light theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SignUpScreenPreviewLight() {
    SignUpScreen()
}

@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun SignUpScreenPreviewDark() {
    SignUpScreen()
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    name = "Small Screen"
)
@Composable
fun SignUpScreenPreviewSmall() {
    SignUpScreen()
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 800,
    name = "Large Screen"
)
@Composable
fun SignUpScreenPreviewLightLarge() {
    SignUpScreen()
}