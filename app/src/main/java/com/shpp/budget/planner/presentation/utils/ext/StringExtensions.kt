package com.shpp.budget.planner.presentation.utils.ext

fun String.toReducedNumberFormat(): String {
    return if (this.length > 3) this.replace(Regex("\\d{3}\$"), "k")
    else this
}