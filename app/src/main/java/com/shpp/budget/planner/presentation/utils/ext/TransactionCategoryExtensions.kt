package com.shpp.budget.planner.presentation.utils.ext

import com.shpp.budget.planner.data.model.TransactionCategory

private typealias CategoryUI = com.shpp.budget.planner.presentation.addScreen.TransactionCategory

fun TransactionCategory.toUICategory() = when (this) {
    TransactionCategory.BEAUTY -> CategoryUI.BEAUTY
    TransactionCategory.CAR -> CategoryUI.CAR
    TransactionCategory.CLOTHES -> CategoryUI.CLOTHES
    TransactionCategory.DONATION -> CategoryUI.DONATION
    TransactionCategory.FOOD -> CategoryUI.FOOD
    TransactionCategory.GIFT -> CategoryUI.GIFT
    TransactionCategory.HEALTH -> CategoryUI.HEALTH
    TransactionCategory.HOME -> CategoryUI.HOME
    TransactionCategory.OTHER -> CategoryUI.OTHER
}

fun CategoryUI.toCategory() = when (this) {
    CategoryUI.BEAUTY -> TransactionCategory.BEAUTY
    CategoryUI.CAR -> TransactionCategory.CAR
    CategoryUI.CLOTHES -> TransactionCategory.CLOTHES
    CategoryUI.DONATION -> TransactionCategory.DONATION
    CategoryUI.FOOD -> TransactionCategory.FOOD
    CategoryUI.GIFT -> TransactionCategory.GIFT
    CategoryUI.HEALTH -> TransactionCategory.HEALTH
    CategoryUI.HOME -> TransactionCategory.HOME
    CategoryUI.OTHER -> TransactionCategory.OTHER
}