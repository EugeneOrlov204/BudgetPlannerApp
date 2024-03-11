package com.shpp.budget.planner.presentation.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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

/**
 * Draw height is extended on top by
 *
 * [barPaddingVertical]+([plusIconSize] + [plusPadding] * 2)*[plusButtonSpacingPercent]
 */
@Composable
fun BottomAppBar(
    currentScreen: BottomBarScreen,
    plusButtonColor: Color = MaterialTheme.colorScheme.secondary,
    plusIconColor: Color = MaterialTheme.colorScheme.onSecondary,
    plusPadding: Dp = dimensionResource(R.dimen.home_screen_central_button_padding),
    plusIconSize: Dp = dimensionResource(R.dimen.home_screen_central_button_icon_size),
    barPaddingVertical: Dp = dimensionResource(R.dimen.home_screen_bottom_bar_padding_vertical),
    barColor: Color = MaterialTheme.colorScheme.tertiary,
    @FloatRange(from = 0.0, to = 1.0) plusButtonSpacingPercent: Float = 0.2f,
    onPlusClick: () -> Unit = {},
    onScreenClick: (screen: BottomBarScreen) -> Unit = {}
) {
    Box(contentAlignment = Alignment.Center) {
        val density = LocalDensity.current
        val plusSize by remember { derivedStateOf { (plusIconSize + plusPadding * 2) } }
        val plusSizePx by remember { derivedStateOf { with(density) { plusSize.toPx() } } }
        val barPaddingVerticalPx by remember {
            derivedStateOf { with(density) { barPaddingVertical.toPx() } }
        }

        Icon(
            modifier = Modifier
                .offset(y = -(plusSize * (1 + plusButtonSpacingPercent)))
                .clickable { onPlusClick() }
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
                            0f, -barPaddingVerticalPx - clearance,
                            (size.width - 1.5f * holeSize) / 2, -barPaddingVerticalPx - clearance,
                            size.width / 2, clearance * 2f,
                            (size.width + 1.5f * holeSize) / 2, -barPaddingVerticalPx - clearance,
                            size.width, -barPaddingVerticalPx - clearance,
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
                .padding(bottom = (barPaddingVertical - plusSize * plusButtonSpacingPercent * 2))
                .offset(y = -plusSize * plusButtonSpacingPercent * 2),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomBarScreen.entries.forEachIndexed { index, bottomBarScreen ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { onScreenClick(bottomBarScreen) }) {
                        Icon(
                            imageVector = bottomBarScreen.icon,
                            contentDescription = null,
                            tint = if (currentScreen == bottomBarScreen) {
                                MaterialTheme.colorScheme.onSecondary
                            } else {
                                MaterialTheme.colorScheme.onTertiary
                            }
                        )
                    }
                }
                if (index == (BottomBarScreen.entries.size / 2) - 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
private fun BottomAppBarPreview() {
    BudgetPlannerAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomAppBar(currentScreen = BottomBarScreen.HOME)
        }
    }
}