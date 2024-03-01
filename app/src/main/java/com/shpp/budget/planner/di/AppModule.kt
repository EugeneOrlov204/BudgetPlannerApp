package com.shpp.budget.planner.di

import com.shpp.budget.planner.data.repository.FirebaseAuthRepository
import com.shpp.budget.planner.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAuthRepository(repository: FirebaseAuthRepository): AuthRepository
}