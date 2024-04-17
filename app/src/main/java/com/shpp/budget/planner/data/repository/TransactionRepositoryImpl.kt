package com.shpp.budget.planner.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.shpp.budget.planner.data.model.Transaction
import com.shpp.budget.planner.domain.repository.TransactionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val YEAR_FIELD_KEY = "year"
const val MONTH_FIELD_KEY = "month"
const val DAY_FIELD_KEY = "day"
const val AMOUNT_FIELD_KEY = "amount"
const val CATEGORY_FIELD_KEY = "category"
const val TRANSACTION_COLLECTION_NAME = "transactions"
const val EXPENSE_COLLECTION_NAME = "expense"
const val INCOME_COLLECTION_NAME = "income"

class TransactionRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) :
    TransactionsRepository {

    override suspend fun addTransaction(userUID: String, transaction: Transaction): Result<Unit> =
        withContext(Dispatchers.IO) {
            callbackFlow<Result<Unit>> {
                if (transaction is Transaction.Expense) {
                    db.collection("$TRANSACTION_COLLECTION_NAME/$userUID/$EXPENSE_COLLECTION_NAME")
                        .add(
                            hashMapOf(
                                YEAR_FIELD_KEY to transaction.year,
                                MONTH_FIELD_KEY to transaction.month,
                                DAY_FIELD_KEY to transaction.day,
                                AMOUNT_FIELD_KEY to transaction.amount,
                                CATEGORY_FIELD_KEY to transaction.category
                            )
                        )
                } else {
                    db.collection("$TRANSACTION_COLLECTION_NAME/$userUID/$INCOME_COLLECTION_NAME")
                        .add(
                            hashMapOf(
                                YEAR_FIELD_KEY to transaction.year,
                                MONTH_FIELD_KEY to transaction.month,
                                DAY_FIELD_KEY to transaction.day,
                                AMOUNT_FIELD_KEY to transaction.amount
                            )
                        )
                }.addOnSuccessListener {
                    trySend(Result.success(Unit))
                }.addOnFailureListener { exception ->
                    trySend(Result.failure(exception))
                }
                awaitClose()
            }.first()
        }
}