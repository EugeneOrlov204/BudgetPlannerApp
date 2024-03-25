package com.shpp.budget.planner.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.shpp.budget.planner.data.repository.FirebaseAuthRepository
import com.shpp.budget.planner.data.repository.TransactionRepositoryImpl
import com.shpp.budget.planner.domain.repository.AuthRepository
import com.shpp.budget.planner.domain.repository.TransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(repository: FirebaseAuthRepository): AuthRepository

    @Binds
    @Singleton
    abstract fun bindTransactionsRepository(repository: TransactionRepositoryImpl): TransactionsRepository

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore
    }
}