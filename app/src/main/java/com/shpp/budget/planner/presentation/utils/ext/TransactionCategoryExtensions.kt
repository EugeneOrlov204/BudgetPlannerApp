package com.shpp.budget.planner.presentation.utils.ext

import com.shpp.budget.planner.data.model.TransactionCategory

typealias CategoryUI = com.shpp.budget.planner.presentation.addScreen.TransactionCategory

fun TransactionCategory.toUICategory() = when (this) {
    TransactionCategory.BEAUTY -> CategoryUI.BEAUTY
    TransactionCategory.CAR -> CategoryUI.CAR
    TransactionCategory.CLOTHES -> CategoryUI.CLOTHES
    TransactionCategory.DONATION -> CategoryUI.DONATION
    TransactionCategory.FOOD -> CategoryUI.FOOD
    TransactionCategory.GIFT -> CategoryUI.GIFT
    TransactionCategory.HEALTH -> CategoryUI.HEALTH
    TransactionCategory.HOME -> CategoryUI.HOME
}