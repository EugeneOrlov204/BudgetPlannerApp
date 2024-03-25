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

class TransactionRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) :
    TransactionsRepository {

    override suspend fun addTransaction(userUID: String, transaction: Transaction): Result<Unit> =
        withContext(Dispatchers.IO) {
            callbackFlow<Result<Unit>> {
                if (transaction is Transaction.Expense) {
                    db.collection("transactions/$userUID/expense")
                        .add(
                            hashMapOf(
                                "year" to transaction.year,
                                "month" to transaction.month,
                                "day" to transaction.day,
                                "amount" to transaction.amount,
                                "category" to transaction.category
                            )
                        )
                } else {
                    db.collection("transactions/$userUID/income")
                        .add(
                            hashMapOf(
                                "year" to transaction.year,
                                "month" to transaction.month,
                                "day" to transaction.day,
                                "amount" to transaction.amount
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