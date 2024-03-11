package com.shpp.budget.planner.domain.model

data class Transaction(
    val typeName:String,
    val date:String,
    val transactionAmount: Float
)
