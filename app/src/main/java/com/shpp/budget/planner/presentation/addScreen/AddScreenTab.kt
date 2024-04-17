package com.shpp.budget.planner.presentation.addScreen

import androidx.annotation.StringRes
import com.shpp.budget.planner.R

enum class AddScreenTab(@StringRes val title: Int) {
    INCOME(R.string.new_income),
    EXPENSES(R.string.new_expense)
}