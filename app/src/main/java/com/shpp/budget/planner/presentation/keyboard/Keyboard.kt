package com.shpp.budget.planner.presentation.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R

const val textButtonNormalTextSize = 20
const val textButtonSmallTextSize = 15

@Composable
fun Keyboard() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        CreateKeyboardRow(
            firstButton = KeyboardButtonsSymbols.BUTTON_1,
            secondButton = KeyboardButtonsSymbols.BUTTON_2,
            thirdButton = KeyboardButtonsSymbols.BUTTON_3,
            fourthButton = KeyboardButtonsSymbols.BUTTON_MINUS
        )
        CreateKeyboardRow(
            firstButton = KeyboardButtonsSymbols.BUTTON_4,
            secondButton = KeyboardButtonsSymbols.BUTTON_5,
            thirdButton = KeyboardButtonsSymbols.BUTTON_6,
            fourthButton = KeyboardButtonsSymbols.BUTTON_PLUS
        )
        CreateKeyboardRow(
            firstButton = KeyboardButtonsSymbols.BUTTON_7,
            secondButton = KeyboardButtonsSymbols.BUTTON_8,
            thirdButton = KeyboardButtonsSymbols.BUTTON_9,
            fourthButton = KeyboardButtonsSymbols.BUTTON_EQUAL
        )
        KeyboardColumnSpacer()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CreateTextButton(text = " ", textButtonNormalTextSize)
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_0))
            CreateTextButton(text = stringResource(id = R.string.keyboard_button_x), textButtonNormalTextSize)
            CreateTextButton(text = stringResource(id = R.string.keyboard_button_done).uppercase(), textButtonSmallTextSize )
        }
        KeyboardColumnSpacer()
    }
}

@Composable
private fun CreateKeyboardRow(
    firstButton: String,
    secondButton: String,
    thirdButton: String,
    fourthButton: String,
) {
    KeyboardColumnSpacer()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CreateFilledButton(firstButton)
        CreateFilledButton(secondButton)
        CreateFilledButton(thirdButton)
        CreateTextButton(
            fourthButton,
            textButtonNormalTextSize
        )
    }
}

@Composable
private fun CreateFilledButton(text: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonColors(Color.White, Color.Black, Color.White, Color.Black),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.keyboard_filled_button_rounded_corner_shape_percents)),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.keyboard_button_height))
            .width(dimensionResource(id = R.dimen.keyboard_button_width))
    ) {
        Text(
            text = text,
            fontSize = dimensionResource(id = R.dimen.keyboard_filled_button_normal_text_size).value.sp
        )
    }
}

@Composable
private fun CreateTextButton(text: String, fontSize: Int) {
    TextButton(
        onClick = { /*TODO*/ },
        colors = ButtonColors(Color.Transparent, Color.Blue, Color.Transparent, Color.Blue),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.keyboard_button_height))
            .width(dimensionResource(id = R.dimen.keyboard_button_width))
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp
        )
    }
}

@Composable
private fun KeyboardColumnSpacer() {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.keyboard_rows_space_size)))
}

@Preview(showSystemUi = true)
@PreviewLightDark
@Composable
fun KeyboardPreview() {
    Keyboard()
}

object KeyboardButtonsSymbols {
    const val BUTTON_1 = "1"
    val BUTTON_2 = "2"
    val BUTTON_3 = "3"
    val BUTTON_4 = "4"
    val BUTTON_5 = "5"
    val BUTTON_6 = "6"
    val BUTTON_7 = "7"
    val BUTTON_8 = "8"
    val BUTTON_9 = "9"
    val BUTTON_0 = "0"
    val BUTTON_MINUS = "-"
    val BUTTON_PLUS = "+"
    val BUTTON_EQUAL = "="
    val BUTTON_X = "x"
}