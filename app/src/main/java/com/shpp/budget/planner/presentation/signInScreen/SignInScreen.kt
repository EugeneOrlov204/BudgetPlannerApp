package com.shpp.budget.planner.presentation.signInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.utils.fontDimensionResource

@Preview(
    showSystemUi = true
)
@Composable
fun SignInScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        colorResource(R.color.sign_up_screen_top_gradient),
                        Color.White
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
                .weight(1f)
        )
    }
}


@Composable
fun Header(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontSize = fontDimensionResource(R.dimen.big_text_size)
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
                .padding(horizontal = dimensionResource(R.dimen.input_field_buttons_horizontal_padding))
        )
        OutlinedTextField(
            value = password, onValueChange = {
                password = it
            },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .offset(y = dimensionResource(R.dimen.divide_input_fields_space))
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.input_field_buttons_horizontal_padding)),
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
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.sign_in_button_offset)))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_corner_radius)),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.sign_in_button_height))
                .padding(horizontal = dimensionResource(R.dimen.input_field_buttons_horizontal_padding)),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            )
        ) {

            Text(
                text = stringResource(id = R.string.sign_in),
                fontSize = fontDimensionResource(R.dimen.sign_in_button_text_size),
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.roboto)),
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
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontSize = fontDimensionResource(R.dimen.medium_text_size),
            fontWeight = FontWeight(500),
            color = colorResource(R.color.google_sign_up_label_text_color)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_spacer)))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.input_field_buttons_horizontal_padding)),
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
                color = colorResource(id = R.color.google_sign_up_label_text_color),
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontSize = fontDimensionResource(R.dimen.medium_text_size),
                fontWeight = FontWeight.Normal
            )

        }
    }
}

@Composable
fun SignUp(modifier: Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.sign_up_button_label),
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            fontFamily = FontFamily(Font(R.font.roboto)),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_spacer)))
        ClickableText(
            text = buildAnnotatedString {
                append(stringResource(R.string.sign_up_button_text))
            },
            onClick = { },
            style = TextStyle(
                fontSize = fontDimensionResource(R.dimen.advanced_text_sze),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto)),
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

