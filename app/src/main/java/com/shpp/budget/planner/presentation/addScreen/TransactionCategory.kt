package com.shpp.budget.planner.presentation.addScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.DryCleaning
import androidx.compose.material.icons.filled.Face3
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class TransactionCategory(val color: Color, val icon: ImageVector) {
    BEAUTY(Color(0xFF5E957D), Icons.Filled.Face3),
    CAR(Color(0xFFFF8C21), Icons.Filled.CarRepair),
    CLOTHES(Color(0xFFA854EA), Icons.Filled.DryCleaning),
    DONATION(Color(0xFFFF7AE2), Icons.Filled.HeartBroken),
    FOOD(Color(0xFF50D244), Icons.Filled.Restaurant),
    GIFT(Color(0xFF0076E3), Icons.Filled.CardGiftcard),
    HEALTH(Color(0xFF1ABF97), Icons.Filled.Healing),
    HOME(Color(0xFFFF5843), Icons.Filled.Home),
    OTHER(Color(0xFF5E5C5C),Icons.Filled.Apps)

}