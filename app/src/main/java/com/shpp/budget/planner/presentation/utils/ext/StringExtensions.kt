package com.shpp.budget.planner.presentation.utils.ext

const val MAX_ALLOWED_AMOUNT_OF_NUMBERS = 3
const val THREE_LAST_NUMBERS_PATTERN = "\\d{$MAX_ALLOWED_AMOUNT_OF_NUMBERS}\$"
fun String.toReducedNumberFormat(): String {
    return if (this.length > MAX_ALLOWED_AMOUNT_OF_NUMBERS) this.replace(
        Regex(THREE_LAST_NUMBERS_PATTERN), "k"
    )
    else this
}