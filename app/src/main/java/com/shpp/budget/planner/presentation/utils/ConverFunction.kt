package com.shpp.budget.planner.presentation.utils

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp

@Composable
@ReadOnlyComposable
fun fontDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp