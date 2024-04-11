package com.shpp.budget.planner.presentation.keyboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R

@Composable
fun Keyboard1() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.keyboard_rows_space_size)))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
               // horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.width(38.dp))
                CreateText(text = "7")
                Spacer(modifier = Modifier.width(76.dp))
                CreateText(text = "8")
                Spacer(modifier = Modifier.width(76.dp))
                CreateText(text = "9")
                Spacer(modifier = Modifier.width(82.dp))
                Text(text = "-", fontSize = 45.sp, color = Color.Blue)
                Spacer(modifier = Modifier.width(6.dp))
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.keyboard_rows_space_size)))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CreateText(text = "4")
                CreateText(text = "5")
                CreateText(text = "6")
                Text(text = "+", fontSize = 45.sp, color = Color.Blue)
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.keyboard_rows_space_size)))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CreateText(text = "1")
                CreateText(text = "2")
                CreateText(text = "3")
                Text(text = "=", fontSize = 45.sp, color = Color.Blue)
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.keyboard_rows_space_size)))
            Row(
                modifier = Modifier
                    //.fillMaxWidth()
                    .align(Alignment.End),
            //horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CreateText(text = "0")
                Spacer(modifier = Modifier.width(66.dp))
                Image(painter = painterResource(id = R.drawable.baseline_arrow_circle_left_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.keyboard_back_arrow_size)))
                Spacer(modifier = Modifier.width(22.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonColors(Color.Blue, Color.White, Color.Blue, Color.White)
                ) {
                    Text(
                        text = stringResource(R.string.keyboard_button_done),
                        fontSize = dimensionResource(id = R.dimen.keyboard_filled_button_normal_text_size).value.sp
                        )
                }
                Spacer(modifier = Modifier.width(6.dp))
            }
    }
    //ConstraintLayout {
    //  val (column, column1, column2, column3) = createRefs()
    // Column(modifier = Modifier.constrainAs(column){
    //   start.linkTo(parent.start, margin = 40.dp)
    //})
    /*Column{
        Text(text = "7", fontSize = 60.sp)
        Text(text = "4", fontSize = 60.sp)
        Text(text = "1", fontSize = 60.sp)
    }
    Column {
        Text(text = "8", fontSize = 60.sp)
        Text(text = "5", fontSize = 60.sp)
        Text(text = "2", fontSize = 60.sp)
    }

    Column {
        Text(text = "9", fontSize = 60.sp)
    }
    Column {
        Text(text = "-", fontSize = 60.sp)
    }*/
    //}

}

@Composable
fun CreateText(text: String){
    Text(text = text, fontSize = dimensionResource(id = R.dimen.keyboard_symbols_size).value.sp)
}

@Preview(showSystemUi = true)
@Composable
fun Keyboard1Preview() {
    Keyboard1()
}
