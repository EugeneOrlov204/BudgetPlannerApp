package com.shpp.budget.planner.presentation.signUpScreen

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
import androidx.compose.material.icons.filled.HideSource
import androidx.compose.material.icons.filled.PanoramaFishEye
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R

@Composable
fun SignUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
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
                .padding(horizontal = 55.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopPart(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                )
                MiddlePart(
                    modifier = Modifier
                        .weight(2.5f)
                        .fillMaxSize()
                )
                BottomPart(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun TopPart(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.sign_up),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun MiddlePart(modifier: Modifier = Modifier) {
    var emailTextState by remember { mutableStateOf("") }
    var passwordTextState by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = emailTextState,
            onValueChange = {
                emailTextState = it
            },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.email)) },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        )

        Spacer(modifier = Modifier.size(25.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = passwordTextState,
            onValueChange = {
                passwordTextState = it
            },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.password)) },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation =
            if (isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation('*'),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                    Icon(
                        imageVector =
                        if (isPasswordVisible)
                            Icons.Filled.PanoramaFishEye
                        else
                            Icons.Filled.HideSource,
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(modifier = Modifier.size(25.dp))

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(id = R.string.create_account),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Composable
fun BottomPart(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.already_a_member),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier
                .clickable {
                    // TODO:  
                },
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight(600),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp
        )
    }
}