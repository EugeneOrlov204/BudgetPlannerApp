package com.shpp.budget.planner.presentation.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.shpp.budget.planner.R
import com.shpp.budget.planner.presentation.theme.BudgetPlannerAppTheme
import com.shpp.budget.planner.presentation.utils.ext.toPath

@Composable
fun BottomAppBar(
    plusButtonColor: Color = MaterialTheme.colorScheme.secondary,
    plusIconColor: Color = MaterialTheme.colorScheme.onSecondary,
    plusPadding: Dp = dimensionResource(R.dimen.home_screen_central_button_padding),
    plusIconSize: Dp = dimensionResource(R.dimen.home_screen_central_button_icon_size),
    barPaddingVertical: Dp = dimensionResource(R.dimen.home_screen_bottom_bar_padding_vertical),
    barColor: Color = MaterialTheme.colorScheme.tertiary,
    @FloatRange(from = 0.0, to = 1.0) plusButtonSpacingPercent: Float = 0.2f
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val density = LocalDensity.current
        val plusSizePx by remember {
            derivedStateOf { with(density) { (plusIconSize + plusPadding * 2).toPx() } }
        }
        val barPaddingVerticalPx by remember {
            derivedStateOf { with(density) { barPaddingVertical.toPx() } }
        }
        Icon(
            modifier = Modifier
                .background(color = plusButtonColor, shape = CircleShape)
                .padding(plusPadding)
                .size(plusIconSize),
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = plusIconColor
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawWithCache {
                    val clearance = plusSizePx * plusButtonSpacingPercent
                    val holeSize = plusSizePx + clearance
                    val polygon = RoundedPolygon(
                        vertices = floatArrayOf(
                            0f, -barPaddingVerticalPx,
                            (size.width - 1.5f * holeSize) / 2, -barPaddingVerticalPx,
                            size.width / 2, clearance * 2f,
                            (size.width + 1.5f * holeSize) / 2, -barPaddingVerticalPx,
                            size.width, -barPaddingVerticalPx,
                            size.width, size.height,
                            0f, size.height
                        ),
                        perVertexRounding = listOf(
                            CornerRounding(0f),
                            CornerRounding(holeSize),
                            CornerRounding(holeSize / 2),
                            CornerRounding(holeSize),
                            CornerRounding(0f),
                            CornerRounding(0f),
                            CornerRounding(0f),
                        )
                    )
                    val path = polygon.cubics.toPath()
                    onDrawBehind {
                        drawPath(path, color = barColor)
                    }
                }
                .padding(bottom = barPaddingVertical),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Wallet,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BottomAppBarPreview() {
    BudgetPlannerAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BottomAppBar()
        }
    }
}