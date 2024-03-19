package com.shpp.budget.planner.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomBarScreen(val icon: ImageVector) {    //TODO change values?
    HOME(Icons.Filled.Home), CALENDAR(Icons.Filled.CalendarToday),
    WALLET(Icons.Filled.Wallet), PERSON(Icons.Filled.Person)
}