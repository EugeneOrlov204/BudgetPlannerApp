package com.shpp.budget.planner.presentation.expenseScreen

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
import com.shpp.budget.planner.R

enum class ExpenseCategory(
    val color: Color,
    val icon: ImageVector,
    val type: Int
) {
    BEAUTY(Color(0xFF5E957D), Icons.Filled.Face3, R.string.expense_screen_beauty),
    CAR(Color(0xFFFF8C21), Icons.Filled.CarRepair, R.string.expense_screen_car),
    CLOTHES(Color(0xFFA854EA), Icons.Filled.DryCleaning, R.string.expense_screen_clothes),
    DONATION(Color(0xFFFF7AE2), Icons.Filled.HeartBroken, R.string.expense_screen_donation),
    FOOD(Color(0xFF50D244), Icons.Filled.Restaurant, R.string.expense_screen_food),
    GIFT(Color(0xFF0076E3), Icons.Filled.CardGiftcard, R.string.expense_screen_gift),
    HEALTH(Color(0xFF1ABF97), Icons.Filled.Healing, R.string.expense_screen_health),
    HOME(Color(0xFFFF5843), Icons.Filled.Home, R.string.expense_screen_home),
    OTHER(Color(0xFF5E5C5C), Icons.Filled.Apps, R.string.expense_screen_other)
}