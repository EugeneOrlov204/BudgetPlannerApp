package com.shpp.budget.planner.presentation.expenseScreen

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shpp.budget.planner.R
import com.shpp.budget.planner.domain.model.Transaction
import com.shpp.budget.planner.presentation.components.BottomAppBar
import com.shpp.budget.planner.presentation.components.BottomBarScreen
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme
import com.shpp.budget.planner.presentation.utils.ext.toReducedNumberFormat
import java.time.Month


@PreviewLightDark
@PreviewScreenSizes
@Composable
fun Preview() {
    BudgetPlannerAppTheme {
        ExpenseScreen {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen(onScreenClick: (screen: BottomBarScreen) -> Unit) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                currentScreen = BottomBarScreen.WALLET,
                onScreenClick = onScreenClick
            )
        }
    ) {

        BottomSheetScaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            sheetDragHandle = null,
            sheetShape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.expense_screen_budget_moth_column_corner_radius),
                topEnd = dimensionResource(R.dimen.expense_screen_budget_moth_column_corner_radius)
            ),
            sheetContent = {
                TransactionsColumn(
                    Modifier
                        .fillMaxWidth(),
                    //Todo get list from viewModel
                    List(12) {
                        Transaction(
                            typeName = "car",
                            date = "11 March 2024",
                            transactionAmount = 500f
                        )
                    },
                )
            },
            sheetPeekHeight = (LocalConfiguration.current.screenHeightDp * 0.42).dp
        ) {
            ExpenseScreenContent()
        }
    }


}

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ExpenseScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Header(
            Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.1f)
        )
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            Month.entries.map { it.toString().take(3) },
            points = listOf(
                1000f,
                204f,
                4556f,
                7000f,
                945f,
                33f,
                4200f,
                9233f,
                1123f,
                735f,
                8004f,
                260f
            ),
            paddingSpace = 20.dp,
        )
        BudgetProgress(totalBudget = 1000, currentBudget = 650)

    }
}


@Composable
fun Header(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.expense_screen_header_text),
            fontSize = dimensionResource(R.dimen.expense_screen_header_text_size).value.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding()
        )
    }
}

@Composable
fun Graph(
    modifier: Modifier,
    xValues: List<String>,
    points: List<Float>,
    paddingSpace: Dp,
) {

    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .background(Color.White)
            .padding(
                horizontal = dimensionResource(R.dimen.expense_screen_graph_horizontal_padding),
                vertical = dimensionResource(R.dimen.expense_screen_graph_vertical_padding)
            )
            .horizontalScroll(scrollState),
        contentAlignment = Alignment.Center
    ) {
        val yValues: MutableList<Int> = getYValues(points)

        val selectedPoint = rememberSaveable { mutableStateOf<PointF?>(null) }

        val graphLinesColor = MaterialTheme.colorScheme.outlineVariant
        val graphValuesTextColor = MaterialTheme.colorScheme.onPrimary
        val selectedValuesColor = MaterialTheme.colorScheme.tertiary
        val selectedPointBigCircleColor = MaterialTheme.colorScheme.surface
        val selectedPointSmallCircleColor = MaterialTheme.colorScheme.secondary
        val budgetLabelColor = MaterialTheme.colorScheme.primary
        val selectedAreaColor = MaterialTheme.colorScheme.error

        val fontSizeGraphValues = dimensionResource(R.dimen.expense_screen_graph_values_font_size)
        val fontSizeExpenseValue =
            dimensionResource(R.dimen.expense_screen_selected_month_budget_font_size)

        val columnWidth = 75
        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .width((points.size * columnWidth).dp)
                .pointerInput(Unit) {
                    //if clicking outside the graph we do nothing
                    detectTapGestures { offset ->
                        if (offset.x > paddingSpace.toPx() + 45)
                            selectedPoint.value = PointF(offset.x, offset.y)
                    }
                },
        ) {
            val yValuesTextWidth = Paint().apply {
                textSize = fontSizeGraphValues.toPx()
            }.measureText(yValues.last().toString().toReducedNumberFormat() + "0")

            val xAxisSpace = (size.width - paddingSpace.toPx() - yValuesTextWidth) / xValues.size
            val yAxisSpace = size.height / yValues.size

            /**calculate the index of the month on which the user clicked*/
            val selectedIndex = if (selectedPoint.value != null) {
                ((selectedPoint.value!!.x - (paddingSpace.toPx() + yValuesTextWidth)) / xAxisSpace).toInt()
            } else -1

            /** placing x axis points */

            val xValuesBottomPadding = 30f
            for (i in xValues.indices) {
                val textColor = if (i == selectedIndex) selectedValuesColor.toArgb()
                else graphValuesTextColor.toArgb()
                drawContext.canvas.nativeCanvas.drawText(
                    xValues[i],
                    xAxisSpace * (i + 1) - 30f,
                    size.height - xValuesBottomPadding,
                    Paint().apply {
                        color = textColor
                        textSize = fontSizeGraphValues.toPx()
                    }
                )
            }

            /** placing y axis point  */
            for (i in yValues.indices) {
                drawContext.canvas.nativeCanvas.drawText(
                    yValues[i].toString().toReducedNumberFormat(),
                    paddingSpace.toPx() / 2f,
                    size.height - yAxisSpace * (i + 1),
                    Paint().apply {
                        color = graphValuesTextColor.toArgb()
                        textSize = fontSizeGraphValues.toPx()
                    }
                )
                /**placing horizontal dividing lines*/
                val pathEffect: PathEffect? = if (i == 0) null
                else
                    PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                drawLine(
                    color = graphLinesColor,
                    start = Offset(
                        paddingSpace.toPx() / 2f + yValuesTextWidth,
                        size.height - yAxisSpace * (i + 1)
                    ),
                    end = Offset(
                        size.width,
                        size.height - yAxisSpace * (i + 1)
                    ),
                    strokeWidth = 4f,
                    cap = StrokeCap.Butt,
                    pathEffect = pathEffect
                )
            }
            /** calculating points coordinates */
            val coordinates: MutableList<PointF> = mutableListOf()
            for (i in points.indices) {
                val x1 = xAxisSpace * (i + 1)
                val y1 =
                    size.height - yAxisSpace - (points[i] / (yValues.last() / (yValues.size - 1))) * yAxisSpace
                coordinates.add(PointF(x1, y1))
            }
            val controlPoints1: MutableList<PointF> = mutableListOf()
            val controlPoints2: MutableList<PointF> = mutableListOf()
            for (i in 1 until coordinates.size) {
                controlPoints1.add(
                    PointF(
                        (coordinates[i].x + coordinates[i - 1].x) / 2,
                        coordinates[i - 1].y
                    )
                )
                controlPoints2.add(
                    PointF(
                        (coordinates[i].x + coordinates[i - 1].x) / 2,
                        coordinates[i].y
                    )
                )
            }
            /**drawing curve */
            val stroke = Path().apply {
                reset()
                moveTo(coordinates.first().x, coordinates.first().y)
                for (i in 0 until coordinates.size - 1) {
                    cubicTo(
                        controlPoints1[i].x, controlPoints1[i].y,
                        controlPoints2[i].x, controlPoints2[i].y,
                        coordinates[i + 1].x, coordinates[i + 1].y
                    )
                }
            }
            drawPath(
                stroke,
                color = graphValuesTextColor,
                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round
                )
            )
            /**  draw if the user has chosen a month*/
            if (selectedIndex != -1 &&
                selectedIndex < xValues.size
            ) {

                val centerX = xAxisSpace * (selectedIndex + 1)
                val centerY =
                    size.height - yAxisSpace - (points[selectedIndex] / (yValues.last() / (yValues.size - 1))) * yAxisSpace

                val selectedMonthGradient = listOf(
                    selectedAreaColor.copy(
                        0.02F,
                        selectedAreaColor.red,
                        selectedAreaColor.green,
                        selectedAreaColor.blue
                    ),
                    selectedAreaColor.copy(
                        0.15F,
                        selectedAreaColor.red,
                        selectedAreaColor.green,
                        selectedAreaColor.blue
                    ),
                )

                val gradientBrush = Brush.verticalGradient(
                    colors = selectedMonthGradient,
                    startY = 0f,
                    endY = size.height
                )
                drawRect(
                    brush = gradientBrush,
                    topLeft = Offset(
                        xAxisSpace * (selectedIndex + 1) - xAxisSpace / 2,
                        0f
                    ),
                    size = Size(xAxisSpace, size.height - yAxisSpace)
                )

                val circleRadius = 30f
                drawCircle(
                    color = selectedPointBigCircleColor,
                    radius = circleRadius,
                    center = Offset(centerX, centerY)
                )
                drawCircle(
                    color = selectedPointSmallCircleColor,
                    radius = circleRadius / 2,
                    center = Offset(centerX, centerY)
                )
                val triangleYOffset = 35f
                val triangleHeight = 20f
                val triangleWidth = 30f
                val triangleStartX = centerX - triangleWidth / 2
                val triangleStartY = centerY - triangleYOffset
                drawPath(
                    path = Path().apply {
                        moveTo(triangleStartX, triangleStartY)
                        lineTo(triangleStartX + triangleWidth, triangleStartY)
                        lineTo(centerX, triangleStartY + triangleHeight)
                        close()
                    },
                    color = budgetLabelColor
                )

                val labelWidth = 200f
                val labelHeight = 70f
                drawRoundRect(
                    color = budgetLabelColor,
                    topLeft = Offset(
                        centerX - labelWidth / 2,
                        centerY - triangleYOffset - labelHeight
                    ),
                    size = Size(labelWidth, labelHeight),
                    cornerRadius = CornerRadius(16f)
                )

                val text = "$${points[selectedIndex]}"
                val paint = Paint().apply {
                    color = Color.White.toArgb()
                    textSize = fontSizeExpenseValue.toPx()
                }
                val textWidth = paint.measureText(text)
                drawContext.canvas.nativeCanvas.drawText(
                    text,
                    centerX - textWidth / 2,
                    centerY - (triangleYOffset + labelHeight) / 2f,
                    paint
                )
            }
        }
    }
}

/**
 * calculates the y-scale values based on the data in the list
 * @param points a list with the total budget for each month
 * returns the value for the y-scale in the graph
 */
fun getYValues(points: List<Float>): MutableList<Int> {
    val max = points.max().toInt()
    var multiplier = 1
    repeat(max.toString().length - 1) { multiplier *= 10 }
    val yValues: MutableList<Int> = mutableListOf()
    for (i in 0..10 step 2) {
        yValues.add(i * multiplier)
    }
    return yValues
}

@Composable
fun BudgetProgress(totalBudget: Int, currentBudget: Int) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(
                    topEnd = dimensionResource(R.dimen.expense_screen_budget_moth_column_corner_radius),
                    topStart = dimensionResource(R.dimen.expense_screen_budget_moth_column_corner_radius)
                )
            ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(
                dimensionResource(R.dimen.expense_screen_budget_progress_top_space)
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.expense_screen_month_budget),
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = "$${totalBudget}",
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(R.dimen.expense_screen_budget_month_amount_text_size).value.sp
            )
        }
        LinearProgressIndicator(
            progress = { currentBudget / totalBudget.toFloat() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = dimensionResource(R.dimen.expense_screen_budget_moth_progress_bar_top_padding))
                .height(dimensionResource(R.dimen.expense_screen_budget_moth_progress_bar_height)),
            strokeCap = StrokeCap.Round
        )
    }
}

@Composable
fun TransactionsColumn(
    modifier: Modifier,
    transactionItems: List<Transaction>,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = dimensionResource(id = R.dimen.expense_screen_cash_transactions_column_vertical_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.expense_screen_between_transaction_item_space))
    ) {
        Text(
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.expense_screen_horizontal_padding),
            ),
            text = stringResource(R.string.expense_screen_expense_column_title_text),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
        )
        for (t in transactionItems) {
            TransactionItem(t.typeName, t.date, t.transactionAmount)
        }

    }
}

@Composable
fun TransactionItem(transactionType: String, transactionDate: String, transactionAmount: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.expense_screen_horizontal_padding))
            .shadow(
                dimensionResource(R.dimen.expense_screen_shadow_size),
                shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius))
            )
            .background(
                shape = RoundedCornerShape(dimensionResource(R.dimen.expense_screen_box_corner_radius)),
                color = MaterialTheme.colorScheme.background
            )
    ) {

        Image(
            painter = painterResource(R.drawable.car_ic),
            contentDescription = null,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.expense_screen_transaction_icon_padding),
                top = dimensionResource(R.dimen.expense_screen_transaction_icon_padding),
                bottom = dimensionResource(id = R.dimen.expense_screen_transaction_icon_padding)
            )
        )
        Spacer(
            modifier =
            Modifier.width(dimensionResource(R.dimen.expense_screen_space_between_transaction_image_and_text))
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = transactionType,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(
                modifier = Modifier.height(dimensionResource(R.dimen.expense_screen_divide_transaction_item_text_space))
            )
            Text(
                text = transactionDate,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
            )
        }
        Text(
            text = transactionAmount.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.weight(0.1f))
    }
}


