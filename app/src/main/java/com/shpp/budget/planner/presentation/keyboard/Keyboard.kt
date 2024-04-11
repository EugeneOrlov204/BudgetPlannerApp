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

@Composable
fun Keyboard() {
    val textButtonNormalTextSize = 20
    val textButtonSmallTextSize = 15
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ColumnSpacer()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_1))
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_2))
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_3))
            CreateTextButton(
                text = stringResource(id = R.string.keyboard_button_minus),
                textButtonNormalTextSize
            )
        }
        ColumnSpacer()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_4))
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_5))
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_6))
            CreateTextButton(
                text = stringResource(id = R.string.keyboard_button_plus),
                textButtonNormalTextSize
            )
        }
        ColumnSpacer()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_7))
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_8))
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_9))
            CreateTextButton(
                text = stringResource(id = R.string.keyboard_button_equals),
                textButtonNormalTextSize
            )
        }
        ColumnSpacer()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CreateTextButton(text = " ", textButtonNormalTextSize)
            CreateFilledButton(text = stringResource(id = R.string.keyboard_button_0))
            CreateTextButton(text = stringResource(id = R.string.keyboard_button_x), textButtonNormalTextSize)
            CreateTextButton(text = stringResource(id = R.string.keyboard_button_done), textButtonSmallTextSize )
        }
        ColumnSpacer()
    }
}

@Composable
fun CreateFilledButton(text: String) {
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
fun CreateTextButton(text: String, fontSize: Int) {
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
fun ColumnSpacer() {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.keyboard_rows_space_size)))
}

@Preview(showSystemUi = true)
@PreviewLightDark
@Composable
fun KeyboardPreview() {
    Keyboard()
}